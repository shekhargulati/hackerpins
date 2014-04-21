<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Hackerpins : Fresh Pins for Hackers</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

        <!-- build:css(.tmp) styles/main.css -->
        <link rel="stylesheet" href="styles/bootstrap.css">
        <link rel="stylesheet" href="styles/toastr.css">
        <link rel="stylesheet" href="styles/main.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.min.css">
        <!-- endbuild -->
</head>
  <body ng-app="hackerpins">
    <!--[if lt IE 7]>
      <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

    <!--[if lt IE 9]>
      <script src="//cdnjs.cloudflare.com/ajax/libs/es5-shim/2.3.0/es5-shim.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/json3/3.3.0/json3.min.js"></script>
    <![endif]-->
    <div class="header container" ng-controller="HeaderCtrl">
        <ul class="nav nav-pills pull-right">
            <li ng-class="{ active: isActive('/')}"><a href="#/">Home</a></li>
            <li ng-class="{ active: isActive('/login')}" ng-if="!isLoggedIn()"><a href="#/login">Login</a></li>
            <li ng-class="{ active: isActive('/register')}" ng-if="!isLoggedIn()"><a href="#/register">Register</a></li>
            <li class="dropdown" ng-if="isLoggedIn()">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" ng-bind="fullname()"></a>
                <ul class="dropdown-menu">
                    <li><a href="#" ng-click="logout()">Logout</a></li>
                </ul>
            </li>
        </ul>
        <h3 class="text-info">HackerPins(Modern HackerNews)</h3>
    </div>

    <div class="container" ng-controller="TabCtrl">
        <ul class="nav nav-tabs nav-justified">
            <li ng-class="{ active: isActive('/')}"><a href="#/"><h4><i class="glyphicon glyphicon-fire"></i> Hot Stories</h4></a></li>
            <li ng-class="{ active: isActive('/stories/upcoming')}"><a href="#/stories/upcoming"><h4><i class="glyphicon glyphicon-flash"></i> Upcoming Stories</h4></a></li>
            <li ng-class="{ active: isActive('/stories/new')}"><a href="#/stories/new"><h4><i class="glyphicon glyphicon-plus"></i> Submit Story</h4></a></li>
        </ul>


        <div ng-view="" class="top-buffer"></div>
    </div>

    <!-- Google Analytics: change UA-XXXXX-X to be your site's ID -->
    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-49772640-1', 'hackerpins.com');
        ga('send', 'pageview');

    </script>

    <script src="js/jquery.js"></script>
    <script src="js/angular.js"></script>
    <script src="js/toastr.js"></script>

        <!-- build:js scripts/plugins.js -->
        <script src="js/affix.js"></script>
        <script src="js/alert.js"></script>
        <script src="js/button.js"></script>
        <script src="js/carousel.js"></script>
        <script src="js/transition.js"></script>
        <script src="js/collapse.js"></script>
        <script src="js/dropdown.js"></script>
        <script src="js/modal.js"></script>
        <script src="js/scrollspy.js"></script>
        <script src="js/tab.js"></script>
        <script src="js/tooltip.js"></script>
        <script src="js/popover.js"></script>
        <!-- endbuild -->

        <!-- build:js scripts/modules.js -->
        <script src="js/angular-resource.js"></script>
        <script src="js/angular-cookies.js"></script>
        <script src="js/angular-sanitize.js"></script>
        <script src="js/angular-route.js"></script>
        <!-- endbuild -->
        <script src="js/moment.js"></script>
        <script src="js/ui-bootstrap.js"></script>
        <script src="js/ui-bootstrap-tpls.js"></script>

        <!-- build:js({.tmp,app}) scripts/scripts.js -->
        <script src="scripts/app.js"></script>
        <script src="scripts/controllers/main.js"></script>
        <script src="scripts/controllers/viewstory.js"></script>
        <script src="scripts/controllers/submitstory.js"></script>
        <script src="scripts/controllers/tabs.js"></script>
        <script src="scripts/controllers/upcoming.js"></script>
        <script src="scripts/controllers/login.js"></script>
        <script src="scripts/controllers/register.js"></script>
        <script src="scripts/controllers/header.js"></script>
        <!-- endbuild -->
</body>
</html>
