@extends('layouts.app')

@section('content')

 <div class="card">
        <div class="card-header"><h1 align="center">Create an Event</h1></div>
       
        <div class="card-body">
           <a href="/events" class="btn btn-info"> Go Back </a>
           <hr>
           
           {!! Form::open(['action'=> 'EventsController@store', 'method'=>'POST','enctype' => 'multipart/form-data']) !!}
               <div class="form-group">
                       {{ Form::label('name','Name')}}
                       {{ Form::text('name', '',['class'=>'form-control', 'placeholder'=>'Event Name'])}}
               </div>
               <div class="form-group">
                       {{ Form::label('venue','Venue')}}
                       {{ Form::text('venue', '',['class'=>'form-control', 'placeholder'=>'Venue'])}}
               </div>
               <div class="form-group">
                       {{ Form::label('event_date','Event Date')}}
                       {{ Form::date('event_date', '',['class'=>'form-control'])}}
               </div>
               <div class="form-group">
                       {{ Form::label('reg_deadline','Registration Deadline')}}
                       {{ Form::date('reg_deadline', '',['class'=>'form-control'])}}
               </div>
<!--
               <div class="form-group">
                        {{ Form::label('event_type','Event Type')}}
                        {{ Form::text('event_type', '',['class'=>'form-control', 'placeholder'=>'Event Type'])}}
                </div>
-->
               <div class="form-group">
                       {{ Form::label('description','Description')}}
                       {{ Form::textarea('description', '', ['class'=> 'form-control', 'placeholder' => 'Write a description'])}}
               </div>
                </div>
                <!--<div class="form-group">
                        {{Form::file('cover_image')}}
                </div>
                -->
               <div class="text-right">
                       {{ Form::submit('Submit', ['class'=>'btn btn-success']) }}
                       {!! Form::close() !!}
               
                </div>
       </div>
       </div>
       
@endsection