@extends('layouts.app')
@section('content')
<div class ="text-center">
    <body background={{ asset('img/logo1.jpg')}}>
        <h1 style="color:lightblue;">Welcome to Eventbook!</h1>
        <p style="color:lightblue;">Largest platform for creating and booking events. Host your event now!</p>
        <p><a class ="btn btn-primary btn-lg" href="/login" role= "button">login</a> 
            <a class= "btn btn-succes btn-lg" href= "/register" role= "button">Register</a>
        </p>
    </body>
</div>
@endsection