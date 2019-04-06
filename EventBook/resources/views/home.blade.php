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
                        <table class="table table-striped">
                            <tr>
                                <th>Title</th>
                                <th></th>
                                <th></th>
                            </tr>

                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
