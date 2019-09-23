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
            <h2 id="ivisionApp.ivision.home.createOrEditLabel">
              <Translate contentKey="ivisionApp.ivision.home.createOrEditLabel">Create or edit a Ivision</Translate>
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
                  <Label id="profile_photoLabel" for="ivision-profile_photo">
                    <Translate contentKey="ivisionApp.ivision.profile_photo">Profile Photo</Translate>
                  </Label>
                  <AvField id="ivision-profile_photo" type="text" name="profile_photo" />
                </AvGroup>
                <AvGroup>
                  <Label id="nicknameLabel" for="ivision-nickname">
                    <Translate contentKey="ivisionApp.ivision.nickname">Nickname</Translate>
                  </Label>
                  <AvField id="ivision-nickname" type="text" name="nickname" />
                </AvGroup>
                <AvGroup>
                  <Label id="genderLabel" for="ivision-gender">
                    <Translate contentKey="ivisionApp.ivision.gender">Gender</Translate>
                  </Label>
                  <AvField id="ivision-gender" type="text" name="gender" />
                </AvGroup>
                <AvGroup>
                  <Label id="birthdayLabel" for="ivision-birthday">
                    <Translate contentKey="ivisionApp.ivision.birthday">Birthday</Translate>
                  </Label>
                  <AvField id="ivision-birthday" type="date" className="form-control" name="birthday" />
                </AvGroup>
                <AvGroup>
                  <Label id="telphoneLabel" for="ivision-telphone">
                    <Translate contentKey="ivisionApp.ivision.telphone">Telphone</Translate>
                  </Label>
                  <AvField id="ivision-telphone" type="text" name="telphone" />
                </AvGroup>
                <AvGroup>
                  <Label id="mailboxLabel" for="ivision-mailbox">
                    <Translate contentKey="ivisionApp.ivision.mailbox">Mailbox</Translate>
                  </Label>
                  <AvField id="ivision-mailbox" type="text" name="mailbox" />
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
