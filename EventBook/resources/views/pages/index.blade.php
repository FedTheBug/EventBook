@extends('layouts.app')
@section('content')
<div class ="text-center">
    <body background={{ asset('img/logo1.jpg')}}>
        <h1 style="color:lightblue;">Welcome to Eventbook!</h1>
        <p style="color:lightblue;">This is the largest platform for upcoming events. Book your events now.</p>
        <p><a class ="btn btn-primary btn-lg" href="/login" role= "button">Login</a> 
            <a class= "btn btn-warning btn-lg" href= "/register" role= "button">Register</a>
        </p>
        <p>
            <a class= "btn btn-danger btn-lg" href= "/auth/google" role= "button">Login with Google</a>
        </p>
    </body>
</div>
@endsection

