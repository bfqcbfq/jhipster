import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ivision from './ivision';
import IvisionDetail from './ivision-detail';
import IvisionUpdate from './ivision-update';
import IvisionDeleteDialog from './ivision-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IvisionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IvisionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IvisionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ivision} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={IvisionDeleteDialog} />
  </>
);

export default Routes;
