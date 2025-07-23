Feature: Validating Place API's

Scenario Outline: Verify if place is begin Scuccesfully added using AddPlaceAPI
  Given Add place payload with "<name>" "<language>" "<address>"
  When user calls "addPlaceAPI" with "POST" http request
  Then the API call got success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"

Examples:
  | name  | language | address           |
  | AmrK  | English  | World cross center|
  | zead  | Arabic   | EGYPT,GIAE        |