var apiGatewayAdminAppConfig = function($routeProvider) {
  console.log('ApiGateWayAdmin App has been loaded');
  $routeProvider
  			// route for the home page
  			/*
  			.when('/', {
  				templateUrl : 'index.html',
  				controller  : 'NavigatorController'
  			})
  			*/

  			// route for the about page
  			.when('/login', {
              				templateUrl : 'views/login.html',
              				controller  : 'LoginController'
              			})
  			.when('/getClient', {
  				templateUrl : 'views/getClient.html',
  				controller  : 'GetClientController'
  			})
  			.when('/createClient', {
  				templateUrl : 'views/createClient.html',
  				controller  : 'createClientController'
  			})
  			.when('/getProvider', {
  				templateUrl : 'views/getProvider.html',
  				controller  : 'GetProviderController'
  			})
  			.when('/createProvider', {
  				templateUrl : 'views/createProvider.html',
  				controller  : 'createProviderController'
  			})
  			.when('/getCapabilities', {
  				templateUrl : 'views/getCapabilities.html',
  				controller  : 'getCapabilitiesController'
  			})
  			.when('/createCapabilities', {
  				templateUrl : 'views/createCapabilities.html',
  				controller  : 'createCapabilitiesController'
  			});

  			/*.otherwise({
  			redirectTo: 'index.html'
  		});
  		*/
};

var ApiGatewayAdminApp = angular.module('ApiGatewayAdminApp', ['ui.bootstrap','ngRoute']).config(apiGatewayAdminAppConfig);