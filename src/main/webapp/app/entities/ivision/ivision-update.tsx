import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './ivision.reducer';
import { IIvision } from 'app/shared/model/ivision.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IIvisionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IIvisionUpdateState {
  isNew: boolean;
}

export class IvisionUpdate extends React.Component<IIvisionUpdateProps, IIvisionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { ivisionEntity } = this.props;
      const entity = {
        ...ivisionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/ivision');
  };

  render() {
    const { ivisionEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterDemoApp.ivision.home.createOrEditLabel">
              <Translate contentKey="jhipsterDemoApp.ivision.home.createOrEditLabel">Create or edit a Ivision</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : ivisionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="ivision-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ivision-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="cust_nameLabel" for="ivision-cust_name">
                    <Translate contentKey="jhipsterDemoApp.ivision.cust_name">Cust Name</Translate>
                  </Label>
                  <AvField
                    id="ivision-cust_name"
                    type="text"
                    name="cust_name"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="cust_sourceLabel" for="ivision-cust_source">
                    <Translate contentKey="jhipsterDemoApp.ivision.cust_source">Cust Source</Translate>
                  </Label>
                  <AvField id="ivision-cust_source" type="text" name="cust_source" />
                </AvGroup>
                <AvGroup>
                  <Label id="cust_industryLabel" for="ivision-cust_industry">
                    <Translate contentKey="jhipsterDemoApp.ivision.cust_industry">Cust Industry</Translate>
                  </Label>
                  <AvField id="ivision-cust_industry" type="text" name="cust_industry" />
                </AvGroup>
                <AvGroup>
                  <Label id="cust_levelLabel" for="ivision-cust_level">
                    <Translate contentKey="jhipsterDemoApp.ivision.cust_level">Cust Level</Translate>
                  </Label>
                  <AvField id="ivision-cust_level" type="text" name="cust_level" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ivision" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  ivisionEntity: storeState.ivision.entity,
  loading: storeState.ivision.loading,
  updating: storeState.ivision.updating,
  updateSuccess: storeState.ivision.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IvisionUpdate);
