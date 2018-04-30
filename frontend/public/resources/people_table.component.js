window.PeopleTable = React.createClass({
    render: function() {

    var rows = this.props.people
        .map(function(entry, i) {
            return (
                <EntryRow
                    key={i}
                    entry={entry}
                    changeAppMode={this.props.changeAppMode} />
            );
        }.bind(this));

        return(
          <div className="panel panel-primary">
            <div className="panel-heading clearfix">
              <h3 class="panel-title pull-left">Matches</h3>
            </div>
            <div class="panel-body">
            {!rows.length
                ? <div className='alert alert-info'>No matches found.</div>
                :
                <table className='table table-bordered table-hover'>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </table>
              }
            </div>
        </div>

        );
    }
});
