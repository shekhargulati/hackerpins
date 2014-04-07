'use strict';

angular.module('hackerpins')
    .controller('ViewstoryCtrl', function ($scope, $http, $routeParams, $location, $modal, $log) {

        var storyId = $routeParams.id;
        $http.get('api/v1/stories/' + storyId).success(function (data, status, headers, config) {
            $scope.story = data;
        }).error(function (data, status, headers, config) {
            $location.path('/');
        });


        $scope.comment = {};
        $scope.open = function (storyId) {

            var modalInstance = $modal.open({
                templateUrl: 'comment.html',
                controller: PostCommentCtrl,
                resolve: {
                    comment: function () {
                        return $scope.comment;
                    }

                }

            });

            modalInstance.result.then(function (data) {
                $scope.selected = data;
                $http.post('http://localhost:8080/hackerpins/api/v1/stories/' + storyId + '/comments', $scope.comment).success(function (data, status, headers, config) {
                    $scope.story.comments.push(data);
                    $scope.comment = {};
                }).error(function (data, status, headers, config) {
                    console.log("Error " + status);
                    console.log("Error " + data);
                });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };
    });


var PostCommentCtrl = function ($scope, $modalInstance, comment) {

    $scope.comment = comment;

    $scope.ok = function () {
        $modalInstance.close(true);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};