'use strict';

angular.module('hackerpins')
    .controller('LoginCtrl', function ($scope, $http, $location, AuthService) {


        $scope.login = function(){
            $http.post('api/v1/profiles/login', $scope.credentials).success(function (data, status, header, config) {
                toastr.success('You are successfully logged in');
                AuthService.setUser(data);
                $location.path('/');
            }).error(function (data, status, header, config) {
                toastr.error('Incorrect username/password combination. Please try again');
                console.log(data);
            });
        }


    });
