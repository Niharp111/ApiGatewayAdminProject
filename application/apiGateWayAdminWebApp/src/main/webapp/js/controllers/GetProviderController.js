ApiGatewayAdminApp.controller('GetProviderController',
     function($scope, $http) {
          console.log('Called the GetProviderController')
             $http({
                          method  : 'GET',
                          url     : 'rest/getProvider',
                      })
               .success(function(data) {
                              console.log(JSON.stringify(data));
                              $scope.providers =data;
                          })
               .error(function(data){
                console.log('error');
               });
});
