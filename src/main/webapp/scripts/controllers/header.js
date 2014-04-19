'use strict';

angular.module('hackerpins')
    .controller('HeaderCtrl', function ($scope, $location, AuthService, $http) {
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        }

        $scope.isLoggedIn = function () {
            if (AuthService.isLoggedIn()) {
                return true;
            }
            return false;
        }

        $scope.fullname = function () {
            return AuthService.getUser().fullname;
        }

        $scope.logout = function () {
            $http.post('api/v1/profiles/logout').success(function (data, header) {
                AuthService.setUser(null);
                toastr.success('You are successfully logged out.');
                $location.path('/');
            }).error(function (data, header) {
                toastr.error('Error in logging out.');
                $location.path('/');
            })
        }

    }
);
