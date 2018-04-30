## Sample Filter client code

###BUILD

This client doesn't require a build or assembly. In order to deploy it, you can copy content of *public* directory into any HTTP server, that supports static content delivery.

For testing purposes you can use e.g  [https://www.npmjs.com/package/http-server](http-server), it can be installed by *npm install -g http-server*

Once you have it, it can be started by *http-server public/* from project directory.
NOTE: the backend has to be started before that.

###DEVELOPMENT AND TESTING

A simple package.json is provided with  the code, you can use it to start things end-to-end or to run tests. The dependencies has to be installed in your development environment prior to that. To install dependencies: *npm install mocha express selenium-webdriver chromedriver*

Once dependencies are done, please start by *npm start* - this command starts backend from docker(if not started already) and starts simple https.
To run tests: *npm test*
