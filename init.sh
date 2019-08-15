#!/usr/bin/env bash
# Selenoid requires Chrome image to be downloaded
# (otherwise webdriver won't start)
docker pull selenoid/vnc:chrome_76.0 && docker-compose up
