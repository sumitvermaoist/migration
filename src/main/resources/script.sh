#!/bin/bash

#1 The API endpoint we want to hit to
URL="https://cd-data-processor-depct-dev.apps.str17.ocp.nonprod.wellsfargo.net/api/jobs/schram"

#2 Output log file where we log all the response in plain text
LOG_FILE="api_response.log"

#3 start time stamp of the request
TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")

#4 Temporary file for body (here mktemp creates a new file and returns its full path to save)
TEMP_BODY=$(mktemp)

#5 Perform request
HTTP_STATUS=$(curl -s -w "%{http_code}" -o "$TEMP_BODY" \
  --location --request GET "$URL" \
  --header "X-APPCERT: depct,66:89:6e:08:00:00:00:0a:cf,2606182154377" \
  --header "wf.senderHostName: localhost" \
  --header "wf.senderApplicationId: DEPCT" \
  --header "X-REQUEST-ID: 40acf7da-0b5c-11e7-93ae-92361f002671" \
  --header "X-WF-CLIENT-ID: ORCHEST1" \
  --header "X-WF-CMP-ID: depct" \
  --header "X-WF-API-VERSION: 0.0.1" \
  --header "Content-Type: application/json" \
  --header "X-WF-ORIG-CLIENT-ID: depct" \
  --header "X-WF-CLIENT-ID: depct" \
  --header "X-WF-RELATION-ID: fb7b7b85-48f1-4fd1-936c-57a1474f4ea3" \
  --header "X-WF-HOST: cd.wellsfargo.com" \
  --header "X-WF-INITIATOR-ID: depct" \
  --header "X-WF-ORIG-SUB-CLIENT-ID: depct" \
  --header "Authorization: Bearer eyJraWQiOiJvcmNoZXN0ZWQtG1bvm5LWTpzc5nFCligxbjZ5vIiBITMjUzQlI0N..." \
  --header "Cookie: e34656ccee2eca3e9572df0db814f013" \
  --data-raw "")

#6 Capture response
RESPONSE=$(cat "$TEMP_BODY")

#7 Here we want to log response alongwith time stamp and http status code
{
  echo "-----"
  echo "Timestamp: $TIMESTAMP"
  echo "HTTP Status: $HTTP_STATUS"
  echo "Response:"
  echo "$RESPONSE"
  echo ""
} >> "$LOG_FILE"

#8 Here we remove that temporary file created above
rm "$TEMP_BODY"

echo "Response logged to $LOG_FILE"
