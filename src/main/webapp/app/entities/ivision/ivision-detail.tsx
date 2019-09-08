import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
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
            <Translate contentKey="jhipsterDemoApp.ivision.detail.title">Ivision</Translate> [<b>{ivisionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="cust_name">
                <Translate contentKey="jhipsterDemoApp.ivision.cust_name">Cust Name</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.cust_name}</dd>
            <dt>
              <span id="cust_source">
                <Translate contentKey="jhipsterDemoApp.ivision.cust_source">Cust Source</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.cust_source}</dd>
            <dt>
              <span id="cust_industry">
                <Translate contentKey="jhipsterDemoApp.ivision.cust_industry">Cust Industry</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.cust_industry}</dd>
            <dt>
              <span id="cust_level">
                <Translate contentKey="jhipsterDemoApp.ivision.cust_level">Cust Level</Translate>
              </span>
            </dt>
            <dd>{ivisionEntity.cust_level}</dd>
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
