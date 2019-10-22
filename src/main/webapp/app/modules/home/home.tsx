import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
  const { account } = props;

  return (
    <Row>
      <Col md="9" className="ocrTips">
        <h2>
          <Translate contentKey="home.title">Welcome, OCR users!</Translate>
        </h2>
        <p className="lead">
          <Translate contentKey="home.subtitle">This is your homepage</Translate>
        </p>
        {account && account.login ? (
          <div className="loginUser">
            <Alert color="success">
              <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                You are logged in as user {account.login}.
              </Translate>
            </Alert>
          </div>
        ) : (
          <div>
            <Alert color="warning">
              <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>
              <Link to="/login" className="alert-link">
                <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
              </Link>
              <Translate contentKey="global.messages.info.authenticated.suffix">
                , you can try the default accounts:
                <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
              </Translate>
            </Alert>

            <Alert color="warning">
              <Translate contentKey="global.messages.info.register.noaccount">You do not have an account yet?</Translate>&nbsp;
              <Link to="/register" className="alert-link">
                <Translate contentKey="global.messages.info.register.link">Register a new account</Translate>
              </Link>
            </Alert>
          </div>
        )}
        <p>
          <Translate contentKey="home.question">If you have any question on OCR:</Translate>
        </p>

        <ul>
          <li>
            <a href="https://ai.baidu.com//" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.homepage">Baidu AI developer platform homepage</Translate>
            </a>
          </li>
          <br/>
          <li>
            <a href="https://ai.baidu.com/tech/ocr" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.stackoverflow">Learn about OCR on Baidu</Translate>
            </a>
          </li>
          <br/>
          <li>
            <a href="https://ai.baidu.com/tech/ocr/iocr" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.bugtracker">Create custom OCR templates on Baidu</Translate>
            </a>
          </li>
          <br/>
          <li>
            <a href="https://ai.baidu.com/docs#/iOCR-Guide/top" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.chat">Custom template text recognition on IOCR</Translate>
            </a>
          </li>
          <br/>
          <li>
            <a href="https://ai.baidu.com/forum/topic/list/164" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.follow">OCR public chat room</Translate>
            </a>
          </li>
        </ul>

        <p>
          <Translate contentKey="home.like">If you have any comments or Suggestions, please let me know</Translate>{' '}
          <a href="http://192.168.20.90/wanglei/jhipster_ivision" target="_blank" rel="noopener noreferrer">
            GitLab
          </a>
          !
        </p>
      </Col>
      <Col md="3" className="pad">
        <Link to="/upload" className="alert-link">
           自定义模板文字识别(IOCR)
        </Link>
      </Col>
      <Col md="3" className="pad">
        <Link to="/upload" className="alert-link">
           通用文字识别(日本語認識可能)
        </Link>
      </Col>
    </Row>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
