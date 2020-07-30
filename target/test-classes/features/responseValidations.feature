Feature: Verify response validation

@GETDetails
Scenario: Verify the Json response
Given GETDetails Payload
When user calls "GETDetailsAPI" with "GET" http request
Then the API call got success with status code 200
And verify response header
And verify response body
And validate response body with data
