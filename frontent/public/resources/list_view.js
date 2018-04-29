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
      filter:{}
    }
  }

    // on mount, fetch all entries and store them as this component's state
    componentDidMount() {
      this.requestFromServer()
    }

    // on unmount, kill fetching if anything is still pending
    componentWillUnmount() {
        this.serverRequest.abort();
    }

    filterChangeHandler(event) {
      console.log(this.state.filter)
      var prop = event.target.name
      var value = event.target.value
      this.setState(function(prevState, props) {
        var newFilter = prevState.filter;
        newFilter[prop] = value;
        return {
          filter: newFilter
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
      this.requestFromServer()
    }
    resetHandler(event) {
      console.log("Reset")
      this.setState({filter: {}});
    }


    // render component on the page
    render() {
        var filteredEntries = this.state.people;
        // $('.page-header h1').text('Filter Matches');


        return (
          <div className="row">
            <div className="col-sm-4">
                  <Filters
                    filters = {this.state.filter}
                    changeHandler = {this.filterChangeHandler}
                    applyHandler = {this.applyHandler}
                    resetHandler = {this.resetHandler}
                    changeAppMode={this.props.changeAppMode} />
            </div>
            <div className="col-sm-6">
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
      return (
        <ReadPeopleComponent />
      )
      }
    }
);
