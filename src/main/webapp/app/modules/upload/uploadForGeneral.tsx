import React from 'react';
import PropTypes, { any, number } from 'prop-types';
import './uploadForGeneral.css';
import './detail.css';
import './typetwodetail.css';
import './typethreedetail.css';
import './loading.css';

import axios from 'axios';
import ReactDOM from 'react-dom';
function guid() {
  function s4() {
    return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
  }
  return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
}

interface ImgProps {
  maxLength: number;
  files: any[];
  onLeave: any;
  url: String;
  cq: 10;
  onEnter: any;
  maxSize: number;
  suffixs: [];
  onError: any;
  multiple: true;
  images: 'image/*';
  isDragover: false;
  filepath: any;
  display: any;
  wordsResult: [{
    words: any;
    location: {
      top: any;
      left: any;
      width: any;
      height: any;
    }
  }];
  loading: any;
  name: any[];
  namedisplay: any;
  documenttype: any;
}
class UploadForGeneral extends React.Component<any, ImgProps> {
  static defaultProps: any =
    {
      onEnter: () => true,
      onLeave: () => true,
      onError: () => true,
      cq: number,
      multiple: true,
      images: 'image/*',
      maxSize: number,
      maxLength: number,
      suffixs: [],
      filepath: any,
      display: any,
      wordsResult: [{
        words: any,
        location: {
          top: any,
          left: any,
          width: any,
          length: any
        }
      }],
      loading: any,
      name: [],
      namedisplay: 'none',
      documenttype: any
    };
  static propTypes: any = {
    onEnter: PropTypes.func,
    onLeave: PropTypes.func,
    onError: PropTypes.func,
    url: PropTypes.string,
    cq: PropTypes.number,
    multiple: PropTypes.bool,
    images: PropTypes.string,
    maxSize: PropTypes.number,
    maxLength: PropTypes.number,
    suffixs: PropTypes.array
  };
  queue: any[];
  uploadQueue: any[];
  uploadingQueue: any[];
  inputRef: any;
  dargRef: any;
  constructor(props: any, context: any) {
    super(props, context);
    this.state = {
      // 上传的文件列表, 无论上传还是失败，success字段会表示文件是否上传成功
      maxLength: 10,
      files: [],
      onLeave: any,
      url: 'http://localhost:8080/api/ocr/general/upload',
      cq: 10,
      onEnter: any,
      maxSize: 10240000 * 10240000,
      suffixs: [],
      onError: any,
      multiple: true,
      images: 'image/*',
      isDragover: false,
      filepath: any,
      display: 'none',
      wordsResult: [{
        words: any,
        location: {
          top: any,
          left: any,
          width: any,
          height: any
        }
      }],
      loading: 'none',
      name: [],
      namedisplay: 'none',
      documenttype: any
    };
    // 等待上传的文件队列
    this.queue = [];
    // 需要上传的队列
    this.uploadQueue = [];
    // 正在上传的队列, 无论上传成功还是上传失败都不会从该队列移除, 避免死循环
    this.uploadingQueue = [];
    // 获取选择文件的值
    this.inputRef = React.createRef();
    // 获取选择文件夹的值
    this.dargRef = React.createRef();
    // 关闭显示详情弹窗
    this.clockClick = this.clockClick.bind(this);
    // 选择文件上传
    // tslint:disable-next-line: unnecessary-bind
    this.handleFileChange = this.handleFileChange.bind(this);
    // 显示错误提示
    this.showError = this.showError.bind(this);
    // 下载错误提示
    this.downLoadError = this.downLoadError.bind(this);
    // 选择文件夹上传
    // tslint:disable-next-line: unnecessary-bind
    this.handleFilesChange = this.handleFilesChange.bind(this);
  }

  handleDrag = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    event.preventDefault();
    event.stopPropagation();
  }

  hanldeDragStart = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    event.preventDefault();
    event.stopPropagation();
  }

  handleDragOver = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    event.preventDefault();
    event.stopPropagation();
    this.setState({
    });
  }

  handleDragEnter = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    event.preventDefault();
    event.stopPropagation();
    this.setState({
    });
  }

  handleDragLeave = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    event.preventDefault();
    event.stopPropagation();
    this.setState({
    });
  }

  handleDragEnd = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    event.preventDefault();
    event.stopPropagation();
    this.setState({
    });
  }
  // 拖拽上传
  handleDrop = (event: { preventDefault: () => void; stopPropagation: () => void; dataTransfer: { files: any; }; }) => {
    const { maxLength } = this.props;
    event.preventDefault();
    event.stopPropagation();
    const files = event.dataTransfer.files;
    if (files.length > maxLength) {
      /* notification.error({ message: `超过最大上传文件数` }) */
      return;
    }
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < files.length; i++) {
      const errMsg = this.check(files[i]);
      if (errMsg) {
        /* notification.error({ message: errMsg }) */
      } else {
        files[i].guid = guid();
        this.addQueue(files[i]);
      }
    }
  }
  // 选择文件上传
  handleFileChange = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    const { maxLength } = this.props;
    event.preventDefault();
    event.stopPropagation();
    const files = this.inputRef.files;
    if (this.state.files + files.length > maxLength) {
      return;
    }
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < files.length; i++) {
      const errMsg = this.check(files[i]);
      if (errMsg) {
      } else {
        files[i].guid = guid();
        this.addQueue(files[i]);
      }
    }
  }
  // 选择文件夹上传
  handleFilesChange = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
    const { maxLength } = this.props;
    event.preventDefault();
    event.stopPropagation();
    const files = this.dargRef.files;
    if (this.state.files + files.length > maxLength) {
      return;
    }
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < files.length; i++) {
      const errMsg = this.check(files[i]);
      if (errMsg) {
      } else {
        files[i].guid = guid();
        this.addQueue(files[i]);
      }
    }
  }

  // 提交上传
  sumbit = async () => {
    const { onLeave, onError, url } = this.props;
    const arr: any[] = [];
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.uploadQueue.length; i++) {
      // 避免重复的上传
      const current = this.uploadQueue[i];
      const filename = current.name;
      arr.push(filename);
      // tslint:disable-next-line: no-console
      console.log(arr);
      this.setState({
        name: arr,
        namedisplay: 'block'
      });
      // tslint:disable-next-line: no-console
      console.log(this.state.name);
      const guids = this.uploadingQueue.map(f => f.guid);
      if (guids.indexOf(current.guid) < 0) {
        this.uploadingQueue = [...this.uploadingQueue, current];
        const uploadFile = new FormData();
        uploadFile.append('file', current);
        // tslint:disable-next-line: no-console
        console.log(uploadFile);
        this.setState({
          loading: 'block'
        });
        axios.post(
          'http://localhost:8080/api/ocr/general/upload',
          uploadFile
        ).then((_: any) => {
          // tslint:disable-next-line: no-console
          console.log(_);
          const filepath = _.data.filepath;
          // tslint:disable-next-line: no-console
          console.log(filepath);
          const message = _.data.errorMessage;
          // tslint:disable-next-line: no-console
          console.log(message);
          const templatetype = _.data.templateType;
          if (filepath !== null && filepath !== undefined && filepath !== '') {
            // tslint:disable-next-line: no-console
            console.log(filepath);
            this.setState(prevState => {
              current.success = true;
              current.filepath = filepath;
              current.templatetype = templatetype;
              return {
                files: [...prevState.files, current]
              };
            }, () => {
              /* notification.success({ message: `${current.name}, 上传成功` }) */
              this.setState({
                loading: 'none',
                namedisplay: 'none'
              });
              onLeave(_);
              this.uploadQueue = this.uploadQueue.filter(f => f.guid !== current.guid);
              this.processQueue();
            });
          } else if (message !== null && message !== undefined && message !== '') {
            // tslint:disable-next-line: no-console
            console.log(message);
            this.setState(prevState => {
              current.success = false;
              current.filepath = filepath;
              return {
                files: [...prevState.files, current]
              };
            }, () => {
              /* notification.success({ message: `${current.name}, 上传成功` }) */
              this.setState({
                loading: 'none',
                namedisplay: 'none'
              });
              onLeave(_);
              this.uploadQueue = this.uploadQueue.filter(f => f.guid !== current.guid);
              this.processQueue();
            });
          }
        }).catch((_: any) => {
          this.setState(prevState => {
            current.success = false;
            return {
              files: [...prevState.files, current]
            };
          }, () => {
            /* notification.error({ message: `${current.name}, 上传失败` }) */
            this.setState({
              loading: 'none',
              namedisplay: 'none'
            });
            onError(_);
            this.uploadQueue = this.uploadQueue.filter(f => f.guid !== current.guid);
          });
        });
      }
    }
  }
  // 加入队列
  addQueue = (file: any) => {
    this.queue = [...this.queue, file];
    this.processQueue();
  }
  //  处理队列
  processQueue = () => {
    const { cq, onEnter } = this.props;
    if (this.uploadQueue.length < cq && this.queue.length > 0) {
      const uploadFile = this.queue.shift();
      // tslint:disable-next-line: no-console
      console.log(uploadFile);
      if (onEnter(uploadFile)) {
        this.uploadQueue = [...this.uploadQueue, uploadFile];
        this.sumbit();
      }
    }
  }
  // 限制上传文件大小
  check = (file: { size: number; name: any; }) => {
    const { maxSize, suffixs } = this.props;
    if (file.size > maxSize * 1024 * 1024) return `${file.name}超过文件大小限制`;
    return undefined;
  }

  getSuffixName = (name: { split: (arg0: string) => String; }) => {
    const names = name.split('.');
    return names[names.length - 1];
  }
  // 删除列表信息;
  // tslint:disable-next-line: no-shadowed-variable
  handleCloseClick = (guid: any) => {
    // tslint:disable-next-line: ter-arrow-body-style
    this.setState(prevState => {
      return {
        files: prevState.files.filter(f => f.guid !== guid)
      };
    });
  }
  // 下载文件
  handleDownLoadClick = (filepath: any) => {
    // tslint:disable-next-line: no-console
    console.log(filepath);
    // tslint:disable-next-line: no-inferrable-types
    const filepaths: string = filepath;
    // tslint:disable-next-line: no-console
    console.log(filepaths);
    axios.get(
      'http://localhost:8080/api/ocr/general/download',
      {
        params: {
          filepath: filepaths
        },
        headers: {
          'Content-Type': 'application/x-www-form-yrlencoded'
        },
        responseType: 'blob'
      })
      // tslint:disable-next-line: only-arrow-functions
      .then(function (response) {
        // tslint:disable-next-line: no-console
        console.log(response);
        if (!response) return;
        const blob = new Blob([response.data], { type: 'application/vnd.ms-excel;charset=utf8' });

        const downloadElement = document.createElement('a');
        const href = window.URL.createObjectURL(blob);
        downloadElement.href = href;
        document.body.appendChild(downloadElement);
        downloadElement.click();
        document.body.removeChild(downloadElement);
        window.URL.revokeObjectURL(href);
      })
      // tslint:disable-next-line: only-arrow-functions
      .catch(function (error) {
      });
  }
  // 显示扫描详情
  handleShowClick = (filepath: any) => {
    const filepaths: string = filepath;
    axios.get(
      'http://localhost:8080/api/ocr/general/showDetails',
      {
        params: {
          filepath: filepaths
        }
      })
      // tslint:disable-next-line: only-arrow-functions
      .then((response: any) => {
        // tslint:disable-next-line: no-console
        console.log(response.data.wordsResult);
        // tslint:disable-next-line: no-inferrable-types
        // 根据后台返回数据 用type判断用哪个模板
        // const type = response.data.type;
        const wordsResultsArr = response.data.wordsResult;
        this.setState({
          display: 'block',
          wordsResult: wordsResultsArr
        });
      })
      // tslint:disable-next-line: only-arrow-functions
      .catch(function (error) {
      });
  }
  //  详情关闭
  clockClick(event: any) {
    this.setState({ display: 'none' });
  }
  // 显示错误提示
  showError(event: any) {
    alert('上传模板有误无法显示，请确认模板');
  }
  // 下载错误提示
  downLoadError(event: any) {
    alert('上传模板有误无法下载，请确认模板');
  }
  // 初始化页面给input框赋值
  componentDidMount() {
    document.getElementById('dragfile').setAttribute('webkitdirectory', ' ');
    document.getElementById('dragfile').setAttribute('directory', ' ');
    document.getElementById('dragfile').setAttribute('multiple', ' ');
  }
  // 初始化页面
  render() {
    const { multiple } = this.props;
    return (
      <div className="" >
        <div className="content">
          <div className="top">
            <ul>
              <li className="firstli">文件名</li>
              <li className="secondli">状态</li>
              <li className="fourli">类型</li>
              <li className="thirdli">操作</li>
            </ul>
            <div className="over" style={{ display: this.state.namedisplay }}>
              {
                // tslint:disable-next-line: ter-arrow-body-style
                this.state.name.map((item, index) => {
                  // tslint:disable-next-line: no-console
                  console.log(this.state.name);
                  // tslint:disable-next-line: no-console
                  console.log(item);
                  // tslint:disable-next-line: no-console
                  console.log(index);
                  return (
                    <div style={{ display: this.state.namedisplay }} key={index}>
                      <span className="fileName">{item}</span>
                      <span className="state">上传中</span>
                      <span className="gengone">-</span>
                      <span className="gengtwo">-</span>
                    </div>
                  );
                })
              }
            </div>
            { /* 上传列表 */}
            <div className="over">
              {
                // tslint:disable-next-line: ter-arrow-body-style
                this.state.files.map(file => {
                  return (
                    // tslint:disable-next-line: jsx-key
                    <div className="allFile" key={file.guid}>
                      <span className="fileName">{file.name}</span>
                      {file.success ? <span className="state">成功</span> : <span>失败/上传的文件有误</span>}
                      {file.success ? <span className="type">Test Demo</span> : <span className="newtype">无</span>}
                      {file.success ? <span className="displayshow" onClick={this.handleShowClick.bind(this, file.filepath)}>查看</span> :
                        <span className="newdisplayshow">查看</span>}
                      {file.success ? <span className="download" onClick={this.handleDownLoadClick.bind(this, file.filepath)}>下载</span> :
                        <span className="download">下载</span>}
                      <span className="del" onClick={this.handleCloseClick.bind(this, file.guid)}>删除</span>
                    </div>
                  );
                })
              }
            </div>
          </div>
          <div className="formDiv">
            <form className="upform" method="post" action="" encType="multipart/form-data"
              onDrag={
                this.handleDrag
              }
              onDragStart={
                this.hanldeDragStart
              }
              onDragEnd={
                this.handleDragEnd
              }
              onDragOver={
                this.handleDragOver
              }
              onDragEnter={
                this.handleDragEnter
              }
              onDragLeave={
                this.handleDragLeave
              }
              onDrop={
                this.handleDrop
              } >
              <input ref={input => this.inputRef = input} onChange={this.handleFileChange} style={{ 'display': 'none' }}
                type="file" id="file" multiple={multiple} accept="image/*" />
              <input ref={input => this.dargRef = input} onChange={this.handleFilesChange} style={{ 'display': 'none' }}
                type="file" id="dragfile" multiple={multiple} accept="image/*" />
              <label className="forlale" htmlFor="file">
                <p className="changeContent">
                  <span>请<span className="choosefile">选择文件</span><span>或</span><span className="choosefile" >拖拽</span>文件到这里</span>
                </p>
              </label>
              <label className="dragover" htmlFor="dragfile">
                <p className="changeContent">
                  <span>请<span className="choosefile">选择文件夹</span></span>
                </p>
              </label>
            </form>
          </div>
        </div>
        {/* {日语识别测试使用模板} */}
        <div className="popLayer" style={{ display: this.state.display }}>
          <span className="close" onClick={this.clockClick}>关闭</span>
          <div className="popBox">
            <div>
              {
                this.state.wordsResult.map((result, index) =>
                    <ul key={index}>
                      <li>{result.words}</li>
                      <li>top:{result.location.top}</li>
                      <li>left:{result.location.left}</li>
                      <li>width:{result.location.width}</li>
                      <li>height:{result.location.height}</li>
                    </ul>
                )
              }
            </div>
          </div>
        </div>

        {/* {アンケート} */}
        <div>
          <div className="ajax-loading" id="ajaxLoading" style={{ display: this.state.loading }}>
            <div className="overlay">&nbsp;</div>
            <div className="loading">
              <img className="loadimg" src="../content/images/load.gif" alt="" />
              <span className="laodspan" >文件解析中，请稍后...</span>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
// 设置默认值类型
UploadForGeneral.propTypes = {
  onEnter: PropTypes.func,
  onLeave: PropTypes.func,
  onError: PropTypes.func,
  url: PropTypes.string,
  cq: PropTypes.number,
  multiple: PropTypes.bool,
  images: PropTypes.string,
  maxSize: PropTypes.number,
  maxLength: PropTypes.number,
  suffixs: PropTypes.array
};
// 设置默认值
UploadForGeneral.defaultProps = {
  // tslint:disable-next-line: ter-arrow-body-style
  onEnter: () => {
    return true;
  },
  // tslint:disable-next-line: ter-arrow-body-style
  onLeave: () => {
    return true;
  },
  // tslint:disable-next-line: ter-arrow-body-style
  onError: () => {
    return true;
  },
  cq: 10, // 限制上传数量
  multiple: true, // 是否开启多个上传 true 是 false 否
  images: 'image/*',
  maxSize: 1024 * 1024,
  maxLength: 10,
  suffixs: []
};
export default UploadForGeneral;
