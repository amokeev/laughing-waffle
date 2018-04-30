// component that decides which main component to load: read or create/update
var MainApp = React.createClass({

    // initial mode is 'read' mode
    getInitialState: function(){
        return {
            currentMode: 'read',
            entryId: null
        };
    },

    // used when use clicks something that changes the current mode
    changeAppMode: function(newMode, entryId){
        this.setState({currentMode: newMode});
            if(entryId !== undefined){
            this.setState({entryId: entryId});
        }
    },

    // render the component based on current or selected mode
    render: function(){

        var modeComponent =
            <ReadPeopleComponent
            changeAppMode={this.changeAppMode} />;

        switch(this.state.currentMode){
            case 'read':
                break;
            case 'readOne':
                modeComponent = <DetailedViewComponent entryId={this.state.entryId} changeAppMode={this.changeAppMode}/>;
                break;
            default:
                break;
        }

        return modeComponent;
    }
});

// go and render the whole React component on to the div with id 'content'
ReactDOM.render(
    <MainApp />,
    document.getElementById('content')
);
