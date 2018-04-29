// main List view
class ReadPeopleComponent extends React.Component {
  constructor(props) {
    super(props);
    this.filterChangeHandler = this.filterChangeHandler.bind(this);
    this.applyHandler = this.applyHandler.bind(this);
    this.resetHandler = this.resetHandler.bind(this);
    this.requestFromServer = this.requestFromServer.bind(this);
    this.state = {
      people:[],
      _filter:{},
      filter:{}
    }
  }

    // on mount, fetch all entries and store them as this component's state
    componentDidMount() {
      this.requestFromServer()
    }
    componentDidUpdate(prevProps, prevState, snapshot) {
      if(prevState.filter != this.state.filter) {
        this.requestFromServer()
      }
    }

    // on unmount, kill fetching if anything is still pending
    componentWillUnmount() {
        this.serverRequest.abort();
    }

    //We are accumulating filter changes in _filter
    filterChangeHandler(event) {
      var prop = event.target.name
      var value = event.target.value
      this.setState(function(prevState, props) {
        var newFilter = prevState._filter;
        newFilter[prop] = value;
        return {
          _filter: newFilter
        }
      });
    }

    requestFromServer() {
      this.serverRequest = $.ajax({
        url: "http://localhost:8080/people",
        data: this.state.filter,
        cache: false,
        type: "GET",
        success: function (filteredEntries) {
          console.log(filteredEntries)
            this.setState({
                people: filteredEntries.matched
            });
        }.bind(this),
        error: function(xhr) {
          console.log("Server side issue")
          console.log(xhr)
        }
});
    }

    applyHandler(event) {
      console.log("Apply")
      event.preventDefault()
      this.setState(function(prevState, props) {
        return {
          filter: prevState._filter
        }
      });
    }
    resetHandler(event) {
      console.log("Reset")
      event.preventDefault();
      this.setState({filter: {},_filter: {}});
    }


    // render component on the page
    render() {
        var filteredEntries = this.state.people;

        return (
          <div className="row">
            <div className="col-sm-5">
                  <Filters
                    filters = {this.state._filter}
                    changeHandler = {this.filterChangeHandler}
                    applyHandler = {this.applyHandler}
                    resetHandler = {this.resetHandler}
                    changeAppMode={this.props.changeAppMode} />
            </div>
            <div className="col-sm-7">
                  <PeopleTable
                      people={filteredEntries}
                      changeAppMode={this.props.changeAppMode} />
           </div>
        </div>
        );
    }
}

  window.ReadPeopleComponent = React.createClass({
      render: function() {
      return (<ReadPeopleComponent />)
      }
    }
);
