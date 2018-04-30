window.EntryRow = React.createClass({
    render: function() {
    return (
        <tr onClick={() => this.props.changeAppMode('readOne', this.props.entry.id)}>
            <td>{this.props.entry.display_name}</td>
            <td>{this.props.entry.age}</td>
            <td>{this.props.entry.compatibility_score}</td>
        </tr>
        );
    }
});
