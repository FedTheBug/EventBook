@extends('layouts.app')

@section('content')

 <div class="card">
        <div class="card-header"><h1 align="center">Edit Event</h1></div>
       
        <div class="card-body">
           <a href="/events" class="btn btn-info"> Go Back </a>
           <hr>
           
           {!! Form::open(['action'=> ['EventsController@update', $event->id], 'method'=>'POST']) !!}
               <div class="form-group">
                       {{ Form::label('name','Name')}}
                       {{ Form::text('name', $event->name,['class'=>'form-control', 'placeholder'=>'Event Name'])}}
               </div>
               <div class="form-group">
                       {{ Form::label('venue','Venue')}}
                       {{ Form::text('venue', $event->venue ,['class'=>'form-control', 'placeholder'=>'Venue'])}}
               </div>
               <div class="form-group">
                       {{ Form::label('event_date','Event Date')}}
                       {{ Form::date('event_date', $event->event_date,['class'=>'form-control'])}}
               </div>
               <div class="form-group">
                       {{ Form::label('reg_deadline','Registration Deadline')}}
                       {{ Form::date('reg_deadline', $event->reg_deadline,['class'=>'form-control'])}}
               </div>
<!--
               <div class="form-group">
                        {{ Form::label('event_type','Event Type')}}
                        {{ Form::text('event_type', $event->event_type,['class'=>'form-control', 'placeholder'=>'Event Type'])}}
                </div>
-->
               <div class="form-group">
                       {{ Form::label('description','Description')}}
                       {{ Form::textarea('description', $event->description,['class'=>'form-control', 'placeholder'=>'Write a description'])}}
               </div>
               {{Form::hidden('_method','PUT')}}
               {{ Form::submit('Submit', ['class'=>'btn btn-success']) }}
           {!! Form::close() !!}
        </div>
       </div>
       </div>
       
@endsection