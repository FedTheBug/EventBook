@extends('layouts.app')

@section('content')
        <a href="/events" class="btn btn-default">Go Back</a>
        <h1 class="card-title">
                {{ $event->name }}
        </h1>
        <p class="card-text">

        Venue: {{ $event->venue }} <br>
        Event Date: {{ $event->event_date }} <br>
        <b> Registration Deadline: {{ $event->reg_deadline}} </b><br> 
        Event Type: {{$event->event_type}}<br>
        Description: {{ $event->description}}
        <hr>
        <a href="/event/{{$event->id}}/edit" class="btn btn-default">Edit</a>

        {!!Form::open(['action' => ['EventsController@destroy',$event->id],
        'method' => 'POST', 'class' => 'float-right'])!!}
                {{Form::hidden('_method','DELETE')}}
                {{Form::submit('Delete',['class'=>'btn btn-danger'])}}
        {!!Form::close()!!}
 @endsection