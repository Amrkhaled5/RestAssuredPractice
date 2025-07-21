Feature: Validating Place API's

Scenario: Verify if place is begin Scuccesfully added using AddPlaceAPI
  Given Add place payload
  When user calls "AddPlaceAPI" with Post http request
  Then the API call got success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"