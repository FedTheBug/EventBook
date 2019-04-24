@extends('layouts.app')
@section('content')

    <head>
            <link href="{{ asset('css/min.css') }}" rel="stylesheet">
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

            <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

    <body background={{ asset('img/black.jpg')}}>

    <div style = "position:relative;top:120px">
    <div class="container">
        <div class="row">
            <div class="container">
                <div class="row">
                    <a href="#" class="intro-banner-vdo-play-btn pinkBg" target="_blank">
                    <i class="glyphicon glyphicon-play whiteText" aria-hidden="true"></i>
                    <span class="ripple pinkBg"></span>
                    <span class="ripple pinkBg"></span>
                    <span class="ripple pinkBg"></span>
                    </a>
                </div>
            </div>
        </div>    
    </div>
</div>
    
    <div style = "position:relative;height:900px">
    <svg viewBox="0 0 1800 600">
            <symbol id="s-text">
                <text text-anchor="middle"
                    x="50%"
                    y="35%"
                    class="webcoderskull"
                    >
                Welcome to 
                </text>
                <text text-anchor="middle"
                    x="50%"
                    y="68%"
                    class="text--line"
                    >
                Eventbook!
                </text>
                
            </symbol>
            
            <g class="g-ants">
                <use xlink:href="#s-text"
                class="webcoderskull-1"></use>     
                <use xlink:href="#s-text"
                class="webcoderskull-1"></use>     
                <use xlink:href="#s-text"
                class="webcoderskull-1"></use>     
                <use xlink:href="#s-text"
                class="webcoderskull-1"></use>     
                <use xlink:href="#s-text"
                class="webcoderskull-1"></use>     
            </g>
    </svg>
    
    <div style = "position:relative; left:40%; top:800px;">
           
        <p><a class ="btn btn-primary btn-lg" href="/login" role= "button">Login</a> 
            <a class= "btn btn-warning btn-lg" href= "/register" role= "button">Register</a>
        </p>
        <p>
            <a class= "btn btn-danger btn-lg" href= "/auth/google" role= "button">Login with Google</a>
        </p>
    </div>
        
        
    </div>
    </body>

@endsection

