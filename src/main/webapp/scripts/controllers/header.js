'use strict';

angular.module('hackerpins')
    .controller('HeaderCtrl', function ($scope, $location, AuthService) {
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        }

        $scope.isLoggedIn = function(){
            if(AuthService.isLoggedIn()){
               return true;
            }
            return false;
        }

        $scope.fullname = function(){
            return AuthService.getUser().fullname;
        }

        $scope.logout = function(){
            alert("Logout..");
        }

    }
);
