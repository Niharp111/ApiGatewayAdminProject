ApiGatewayAdminApp.controller('LoginController',
function($scope,$http,$rootScope){
   console.log('Called the LoginController')


   $scope.submitForm = function() {
          console.log('Called the login rest')
             $http({
                          method  : 'POST',
                          url     : 'rest/login',
                          data    : JSON.stringify({username : $scope.admin.userName, password : $scope.admin.password}),  // pass in data as strings
                          headers : { 'Content-Type': 'application/json' }
                      })
               .success(function(data) {
                              console.log(JSON.stringify(data));
                              $scope.message = data;
                              $rootScope.isLogin = true;
                          })
               .error(function(data){
                console.log('error');
               });

         alert($scope.message);

    }
}
);
