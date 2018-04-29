class NumericInput extends React.Component {
  constructor(props) {
    super(props);
    this.name = props.name
    this.label = props.label
    this.handleChange = this.props.changeHandler.bind(this);
  }

  render() {
      return(
        <div className="form-group row">
          <label for={this.name}  class="col-sm-4 col-form-label">{this.label}</label>
          <div className="col-sm-6">
            <input type="number" name={this.name} className="form-control input-small" value={this.props.filters[this.name]} step={this.props.step} min={this.props.min} max={this.props.max} onChange={this.handleChange}/>
          </div>
        </div>
      );
  }
}

class RangeInput extends React.Component {
  constructor(props) {
    super(props);
    this.name = props.name
    this.label = props.label
    this.handleChange = this.props.changeHandler.bind(this);
  }

  render() {
      return(
        <div className="form-group row">
          <label for={this.name + "_min"} className="col-sm-4 col-form-label">{this.label}</label>
          <div className="col-sm-6">
            <div className="input-group">
              <input type="number" name={this.name + '_min'} className="input-small form-control" value={this.props.filters[this.name + '_min']} step={this.props.step} min={this.props.min} max={this.props.max} onChange={this.handleChange}/>
              <span className="input-group-addon">to</span>
              <input type="number" name={this.name + '_max'} className="input-small form-control" value={this.props.filters[this.name + '_max']} step={this.props.step} min={this.props.min} max={this.props.max} onChange={this.handleChange}/>
            </div>
          </div>
        </div>
      );
  }
}


class YesNoRadioButton extends React.Component {
  constructor(props) {
    super(props);
    this.label = props.label
    this.name = props.name
    this.handleChange = props.changeHandler.bind(this);
  }

    render() {
        return(
          <div className="form-group row">
            <label className="col-sm-4 col-form-label">{this.label}</label>
            <div className="col-sm-6 row">
              {/* <fieldset disabled> */}
              <div className="form-check form-check-inline col-sm-2">
                <input className="form-check-input" type="radio" name={this.name} checked={this.props.filters[this.name] == 'true'} id="has_photo_yes" value="true" onChange={this.handleChange} />
                <label className="form-check-label">Yes</label>
              </div>
              <div className="form-check form-check-inline col-sm-2">
                <input className="form-check-input" type="radio" name={this.name} checked={this.props.filters[this.name] == 'false'} id="has_photo_no" value="false" onChange={this.handleChange} />
                <label className="form-check-label">No</label>
              </div>
            {/* </fieldset> */}
            </div>
        </div>
        );
    }
}


window.Filters = React.createClass({
    render: function() {
    return (
      <div className="panel panel-primary">
        <div className="panel-heading">
          <div className="row">
            {/* <div className="col-sm-4"><h3 className="panel-title">Filters</h3></div> */}
              <div className="col-sm-2">
                <button type="button" className="btn btn-primary" onClick={this.props.applyHandler}>apply</button>
              </div>
              <div className="col-sm-2">
                <button type="button" className="btn btn-primary" onClick={this.props.resetHandler}>reset</button>
              </div>
          </div>
        </div>
        <div className="panel-body">
          <YesNoRadioButton name="has_photo" label="Has photo" filters={this.props.filters} changeHandler={this.props.changeHandler}/>
          <YesNoRadioButton name="contact" label="In contact" filters={this.props.filters} changeHandler={this.props.changeHandler}/>
          <YesNoRadioButton name="favorite" label="Favorite" filters={this.props.filters} changeHandler={this.props.changeHandler}/>
          <RangeInput name="compatibility_score" label="Compatibility Score"  filters={this.props.filters} min="0.1" max="0.99" step="0.01" changeHandler={this.props.changeHandler}/>
          <RangeInput name="age" label="Age" filters={this.props.filters} min="18" max="95" step="1" changeHandler={this.props.changeHandler}/>
          <RangeInput name="height" label="Height" filters={this.props.filters} min="135" max="210" step="1" changeHandler={this.props.changeHandler}/>
          <NumericInput name="distance" label="Distance" filters={this.props.filters} min="30" max="300" step="1" changeHandler={this.props.changeHandler}/>
        </div>
      </div>
    );
    }
});
