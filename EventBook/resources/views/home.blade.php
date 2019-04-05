@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header" align="center">Dashboard</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif
                    <h3>You are logged in!</h3>
                    <div class  = "panel-body">
                        <a href="/events/create" class = "btn btn-primary">Create Event</a>
                    </div>
                    
                    
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
