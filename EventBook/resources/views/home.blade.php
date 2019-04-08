@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header" style="background-color:lightblue" align="center">
                        <font size="+2">Dashboard</font>
                </div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif
                    <h5>You are logged in!</h5>
                    <div class="text-center">
                        <hr>
                        <a href="/events/create" class = "btn btn-info">Create Event</a>
                    </div>
                    <div>
                        <hr>
                        <h5 >Your Events:</h5>
                    </div>

                    @if(count($events) > 0)
                        <table class="table table-striped">
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                            @foreach ($events as $event)
                                <tr>
                                    <td>{{$event->name}}</td>
                                    <td><a href="/events/{{$event->id}}/edit" class="btn btn-success">Edit</a></td>
                                    <td>
                                        {!!Form::open(['action' => ['EventsController@destroy',$event->id],
                                        'method' => 'POST', 'class' => 'float-right'])!!}
                                                {{Form::hidden('_method','DELETE')}}
                                                {{Form::submit('Delete',['class'=>'btn btn-danger'])}}
                                        {!!Form::close()!!}
                                    </td>
                                </tr>
                            @endforeach
                        </table>
                    @else
                        <p>You have no Events</p>
                    @endif

                </div>
            </div>
        </div>
    </div>
</div>
@endsection
