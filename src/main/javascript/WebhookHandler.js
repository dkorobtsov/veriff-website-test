/**
 * ----------------------------------------------------------------------------------------
 * NB:
 * Following code is not related to this particular project.
 * It's my solution for bonus assignment (and not meant to be executed here):
 * ----------------------------------------------------------------------------------------
 * Bonus
 *
 * If you can build a service that integrates with Veriff and listens for webhooks based on
 * documentation here https://veriff.me/developers
 *
 * ----------------------------------------------------------------------------------------
 *
 * Since no restrictions or requirements were specified how to solve this assignment, I just created
 * simple notification bot to help me with testing and deployed it as AWS Lambda function using Node.JS runtime.
 *
 * How it works?
 *
 * <pre>
 * 1. Veriff Webhook -->  2. Amazon API Gateway
 *                              ↓
 *                        3. Lambda Proxy
 *                              ↓
 *                        4. Event Handler           --> 5. CloudWatch Logs
 *                           (this code snippet)
 *                              ↓
 *                        6. Amazon SES
 *                           (Simple Email Service)
 * </pre>
 *
 * 1. Once verification event is fired it's forwarded to Amazon API Gateway Endpoint
 * 2. Gateway endpoint listens for incoming POST requests, matching events are handled by proxy
 * 3. Matching event is converted by proxy to AWS event and sent to Lambda
 * 4. Event handler returns error if body does not contain validation result. Otherwise event passed to SES.
 * 5. All Lambda invocations are registered in CloudWatch logs and can be traced back.
 * 6. If validation result was found, SES sends email with notification to specified email address.
 *
 * Verified that bot works as intended in scope of verification testing session.
 */
const AWS = require("aws-sdk");

exports.handler = (event, context, callback) => {
    console.log('Received event:', JSON.stringify(event, null, 2));

    const done = (err, res) => callback(null, {
        statusCode: err ? '400' : '200',
        body: err ? err.message : JSON.stringify(res),
        headers: {
            'Content-Type': 'application/json',
        },
    });

    function sendEmail(message) {
        const mail = {
            Destination: {
                ToAddresses: ["some.email@gmail.com"]
            },
            Message: {
                Body: {
                    Html: {
                        Charset: "UTF-8",
                        Data: `<p>"${message}"</p>`
                    },
                    Text: {
                        Charset: "UTF-8",
                        Data: message
                    }
                },
                Subject: {
                    Charset: "UTF-8",
                    Data: "Verification status update"
                }
            },
            Source: "Veriff AWS Bot <some.email@gmail.com>"
        };

        const sendPromise = new AWS.SES({apiVersion: "2010-12-01"})
            .sendEmail(mail)
            .promise();

        sendPromise
            .then(data => {
                console.log(data.MessageId);
                context.done(null, "Success");
            })
            .catch(err => {
                console.error(err, err.stack);
                context.done(null, "Failed");
            });
    }

    if (event.httpMethod !== 'POST') {
        done(new Error(`Unsupported method "${event.httpMethod}"`))
    } else {
        let json;
        try {
            json = JSON.parse(event.body);
            console.log(json)
        } catch (e) {
            try {
                // Additional attempt - to handle case when body is not escaped.
                // Mostly for testing purposes
                json = JSON.parse(JSON.stringify(event.body))
            } catch (e) {
                json = null;
                console.error('Failed to parse body: ', event.body)
            }
        }

        let status = json == null ? undefined : 'unknown';
        if (json != null) {
            try {
                status = json.verification.status;
            } catch (e) {
                console.error("Failed to identify verification status.");
                status = "unknown"
            }
        }

        let message;
        switch (status) {
            case 'approved':
                message = `Verification request approved for: ${json.verification.id}`;
                callback(null, {
                    statusCode: '200',
                    body: message,
                    headers: {
                        'Content-Type': 'text/plain',
                    },
                });

                sendEmail(message);
                break;

            case undefined:
            case 'unknown':
                done(new Error(`Invalid request body`));
                break;

            default:
                message = `Verification request failed for: ${json.verification.id}`
                done(new Error(message));
                sendEmail(message);
                break
        }
    }
};
