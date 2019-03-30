@extends('layouts.app')

@section('content')
<a href="/events" class="btn btn-default">Go Back</a>

<h1 class="card-title">
        {{ $event->name }}</h1>
        <p class="card-text">
               
                Venue: {{ $event->venue }} <br>
                Event Date: {{ $event->event_date }} <br>
            <b> Registration Deadline: {{ $event->reg_deadline}} </b><br><br> 
            Description: {{ $event->description}}
           
            </div>
        </p>

@endsection

