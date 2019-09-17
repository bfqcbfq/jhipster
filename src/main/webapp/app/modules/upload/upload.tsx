import './upload.scss';

import React from 'react';
import { Row, Col, Alert } from 'reactstrap';
import PropTypes from 'prop-types';
import axios from 'axios';

export class Upload extends React.Component {

  render() {
    return (
      <div>
          <form action="http://localhost:8080/api/upload" method="post" encType ="multipart/form-data">
              <input type="file" name="file"/>
              <input type="submit" value="上传"/>
          </form>
      </div>
           );
  }
}

export default Upload;
