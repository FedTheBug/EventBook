@extends('layouts.app')

@section('content')


    <h1>Events</h1>

    @if(count($events)>0)
@foreach($events as $event)
<div class= 'well'>
<h3><a href ="/events/{{$event->id}}">{{$event->name}}</a></h3>
<small>Deadline: {{$event->reg_deadline}}</small>
</div>
@endforeach
@else 

<p>No Events Found</p>
@endif

@endsection

