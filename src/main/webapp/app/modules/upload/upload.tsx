import React from 'react';
import PropTypes, { any, number } from 'prop-types';
import './upload.css';
import axios from 'axios';
  function guid() {
    function s4() {
      return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
  }

  interface ImgProps {
    maxLength: number;
    files: [];
    onLeave: any;
    url: String;
    cq: 3;
    onEnter: any;
    maxSize: number;
    suffixs: [];
    onError: any;
    multiple: false;
  }
  class Upload extends React.Component<ImgProps> {
    static defaultProps: any =
    { onEnter: () => true,
      onLeave: () => true,
      onError: () => true,
      cq: number,
      multiple: false,
      maxSize: number,
      maxLength: number,
      suffixs: []
    };
    static propTypes: any = {
      onEnter: PropTypes.func,
      onLeave: PropTypes.func,
      onError: PropTypes.func,
      url: PropTypes.string,
      cq: PropTypes.number,
      multiple: PropTypes.bool,
      maxSize: PropTypes.number,
      maxLength: PropTypes.number,
      suffixs: PropTypes.array
    };
    queue: any[];
    uploadQueue: any[];
    uploadingQueue: any[];
    inputRef: any;
    constructor(props: any) {
      super(props);
      this.state = {
        // 上传的文件列表, 无论上传还是失败，success字段会表示文件是否上传成功
        files: [],
        isDragover: false
      };
      // 等待上传的文件队列
      this.queue = [];
      // 需要上传的队列
      this.uploadQueue = [];
      // 正在上传的队列, 无论上传成功还是上传失败都不会从该队列移除, 避免死循环
      this.uploadingQueue = [];
      this.inputRef = React.createRef();
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
        isDragover: true
      });
    }

    handleDragEnter = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
      event.preventDefault();
      event.stopPropagation();
      this.setState({
        isDragover: true
      });
    }

    handleDragLeave = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
      event.preventDefault();
      event.stopPropagation();
      this.setState({
        isDragover: false
      });
    }

    handleDragEnd = (event: { preventDefault: () => void; stopPropagation: () => void; }) => {
      event.preventDefault();
      event.stopPropagation();
      this.setState({
        isDragover: false
      });
    }

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

    sumbit = async () => {
      const { onLeave, onError, url } = this.props;
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < this.uploadQueue.length; i++) {
        // 避免重复的上传
        const current = this.uploadQueue[i];
        const guids = this.uploadingQueue.map(f => f.guid);
        if (guids.indexOf(current.guid) < 0) {
          this.uploadingQueue = [...this.uploadingQueue, current];
          const uploadFile = new FormData();
          uploadFile.append('file', current);
          axios.post(
            'http://localhost:8080/api/upload',
            uploadFile
          ).then((_: any) => {
            this.setState(prevState => {
              current.success = true;
              return {
              };
            }, () => {
              /* notification.success({ message: `${current.name}, 上传成功` }) */
            alert('上传成功');
              onLeave(_);
              this.uploadQueue = this.uploadQueue.filter(f => f.guid !== current.guid);
              this.processQueue();
            });
          }).catch((_: any) => {
            this.setState(prevState => {
              current.success = false;
              return {
              };
            }, () => {
              /* notification.error({ message: `${current.name}, 上传失败` }) */
            alert('上传失败');
              onError(_);
              this.uploadQueue = this.uploadQueue.filter(f => f.guid !== current.guid);
            });
          });
        }
      }
    }

    addQueue = (file: any) => {
      this.queue = [...this.queue, file];
      this.processQueue();
    }

    processQueue = () => {
      const { cq, onEnter } = this.props;
      if (this.uploadQueue.length < cq && this.queue.length > 0) {
        const uploadFile = this.queue.shift();
        if (onEnter(uploadFile)) {
          this.uploadQueue = [...this.uploadQueue, uploadFile];
          this.sumbit();
        }
      }
    }

    check = (file: { size: number; name: any; }) => {
      const { maxSize , suffixs } = this.props;
      if (file.size > maxSize * 1024) return `${file.name}超过文件大小限制`;
      return undefined;
    }

    getSuffixName = (name: { split: (arg0: string) => String; }) => {
      const names = name.split('.');
      return names[names.length - 1];
    }

    // tslint:disable-next-line: no-shadowed-variable
    handleCloseClick = (guid: any) => {
      // tslint:disable-next-line: ter-arrow-body-style
      this.setState(prevState => {
        return {
        };
      });
    }

    render() {
      const { multiple } = this.props;
      return (
     <div className = "" >
        <div className = "content">
        <div className = "top">
            <ul>
              <li className="firstli">文件名</li>
              <li className="secondli">状态</li>
              <li className="thirdli">操作</li>
            </ul>
        </div>
        <form className = "" method = "post" action = "" encType = "multipart/form-data"
          onDrag = {
            this.handleDrag
          }
          onDragStart = {
            this.hanldeDragStart
          }
          onDragEnd = {
            this.handleDragEnd
          }
          onDragOver = {
            this.handleDragOver
          }
          onDragEnter = {
            this.handleDragEnter
          }
          onDragLeave = {
            this.handleDragLeave
          }
          onDrop = {
            this.handleDrop
          } >
              <input ref={input => this.inputRef = input} style = {{ 'display': 'none' }} type = "file" id = "file" multiple = {multiple}/> ,
          <label className = "forlale" htmlFor = "file">
          <p className="changeContent">
          <span className="choosefile">选择文件</span>
          <span>或<span className="choosefile" >拖拽</span>文件到这里</span>
          </p>
          </label>
      </form>
      </div>
     </div>
      );
    }
  }

  Upload.propTypes = {
    onEnter: PropTypes.func,
    onLeave: PropTypes.func,
    onError: PropTypes.func,
    url: PropTypes.string,
    cq: PropTypes.number,
    multiple: PropTypes.bool,
    maxSize: PropTypes.number,
    maxLength: PropTypes.number,
    suffixs: PropTypes.array
  };

  Upload.defaultProps = {
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
    cq: 3,
    multiple: false,
    maxSize: 1024,
    maxLength: 5,
    suffixs: []
  };
  export default Upload;
