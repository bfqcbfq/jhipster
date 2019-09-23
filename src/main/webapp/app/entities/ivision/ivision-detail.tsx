import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ivision.reducer';
import { IIvision } from 'app/shared/model/ivision.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIvisionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class IvisionDetail extends React.Component<IIvisionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { ivisionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="ivisionApp.ivision.detail.title">Ivision</Translate> [<b>{ivisionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="profile_photo">
                <Translate contentKey="ivisionApp.ivision.profile_photo">Profile Photo</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.profile_photo}</dd>
            <dt>
              <span id="nickname">
                <Translate contentKey="ivisionApp.ivision.nickname">Nickname</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.nickname}</dd>
            <dt>
              <span id="gender">
                <Translate contentKey="ivisionApp.ivision.gender">Gender</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.gender}</dd>
            <dt>
              <span id="birthday">
                <Translate contentKey="ivisionApp.ivision.birthday">Birthday</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={ivisionEntity.birthday} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="telphone">
                <Translate contentKey="ivisionApp.ivision.telphone">Telphone</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.telphone}</dd>
            <dt>
              <span id="mailbox">
                <Translate contentKey="ivisionApp.ivision.mailbox">Mailbox</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.mailbox}</dd>
          </dl>
          <Button tag={Link} to="/entity/ivision" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/ivision/${ivisionEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ ivision }: IRootState) => ({
  ivisionEntity: ivision.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IvisionDetail);
