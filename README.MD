# Line Todo Chatbot

## Requirement

- Java 8
- spring Boot 2.x
- postgresql

## Tool

- ngrok see ->(https://medium.com/linedevth/linebot-ngrok-b319841a49d7)
- docker-compose

## Detail for now

### Specifications

1. End-user can create todo list from input text to chat by using these formats. (done)
1. task : date/month/year : time e.g. Buy milk : 2/5/18 : 13:00  (done)
1. task : today : time e.g. Finish writing shopping list : today : 15:30  (done)
1. task : Tomorrow : time e.g. Watch movie : tomorrow : 18:00  (done)
   ** If not Specific time then use defuault time as 12:00 pm**
1. End-user can edit todo list by input word edit in chat and system will return webview link which navigate to edit page
1. System order task in edit page by ascending date  (done only API)
1. End-user can mark task in edit page as important, and it will always pin on top  (done only API)
1. End-user can finish task by clicking on checkbox of that tas (done only API)
1. System send summary of tasks to be done, and tasks completed on daily basis at every 12:00 pm and 6:00 pm.
1. No need to style UI. Any css framwork suuch as bootstrap is allowed

### Requirements:

1. Write unit test.
2. Write Dockerfile to deploy.
3. Write README for installing description.
4. Commit git in proper way.
5. Webview must use LINE's weblogin.
6. Deploy code on any cloud (Please use any free tier cloud such as https://www.heroku.com/,
   https://zeit.co/, or others)
7. Submit BOT QR code along with git repo to our email above.(Find QR code inside LINE developer
   console you have registered)

### Refactor

Exception translate

Implement  Oauth token response


