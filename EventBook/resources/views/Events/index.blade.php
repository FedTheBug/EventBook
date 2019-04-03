@extends('layouts.app')

@section('content')

    <h1>Events</h1>

    @if(count($events)>0)
    @foreach($events as $event)
        <div class= 'well' >
            <h3><a href ="/events/{{$event->id}}">{{$event->name}}</a></h3>
            <small>Event Date: {{$event->event_date}}</small>
            <hr>
        </div>
    @endforeach
    {{$events->links()}}
    @else 
        <p>No Events Found</p>
    @endif

@endsection

