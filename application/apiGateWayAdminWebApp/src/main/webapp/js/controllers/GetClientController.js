ApiGatewayAdminApp.controller('GetClientController',
     function($scope, $http,$location,$rootScope) {
        if($rootScope.isLogin == true){
          console.log('Called the GetClientController')
             $http({
                          method  : 'GET',
                          url     : 'rest/getClient',
                      })
               .success(function(data) {
                              console.log(JSON.stringify(data));
                              $scope.clients =data;
                          })
               .error(function(data){
                console.log('error');
               });
        }else{
          $location.path('/login');
        }
});
