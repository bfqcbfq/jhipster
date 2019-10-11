import React from 'react';
import PropTypes, { any, number } from 'prop-types';
import './upload.css';
import './detail.css';
import './typetwodetail.css';
import './typethreedetail.css';
import './loading.css';

import axios from 'axios';
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
    title: any;
    deliverMessage: [];
    deliveryNo: any;
    address: any;
    contactNUmber: any;
    deliveryCompany: any;
    deliveryDate: any;
    handler: any;
    note: any;
    picker: any;
    deliveryDetails: [{
      batchNo: any;
      brand: any;
      comment: any;
      date: any;
      materialNo: any;
      quantity: any;
      singleWeight: any;
      storehouseNo: any;
      totalWeight: any;
      unit: any;
    }];
    displayTwo: any;
    displayThree: any;
    adress: any;
    companyPhone: any;
    csahier: any;
    customerName: any;
    customerPhone: any;
    customerSign: any;
    deliveryer: any;
    invoiceType: any;
    mainBusiness: any;
    orderMaker: any;
    page: any;
    receiver: any;
    settleStyle: any;
    totalAccount: any;
    totalAmountBig: any;
    totalAmountSmall: any;
    ydDeliveryDetails: [{
      orderNumber: any;
      partsNumber: any;
      partsName: any;
      vehicleType: any;
      productionAarea: any;
      unitPrice: any;
      quantity: any;
      account: any;
      comment: any;
      unit: any;
    }];
    businessCode: any;
    deliverySign: any;
    handlerSign: any;
    totalAmount: any;
    totalQuantity: any;
    MxDeliveryDetails: [{
       styleNo: any;
       style: any;
       color: any;
       unit: any;
       modelS: any;
       modelM: any;
       modelL: any;
       subtotal: any;
       unitPrice: any;
       account: any;
       comment: any;
    }];
    loading: any;
    name: any[];
    namedisplay: any;
    documenttype: any;
  }
  class Upload extends React.Component<any, ImgProps> {
    static defaultProps: any =
    { onEnter: () => true,
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
      title: any,
      deliverMessage: [],
      deliveryNo: any,
      address: any,
      contactNUmber: any,
      deliveryCompany: any,
      deliveryDate: any,
      handler: any,
      note: any,
      picker: any,
      deliveryDetails: [{
        batchNo: any,
        brand: any,
        comment: any,
        date: any,
        materialNo: any,
        quantity: any,
        singleWeight: any,
        storehouseNo: any,
        totalWeight: any,
        unit: any
      }],
      displayTwo: any,
      displayThree: any,
      adress: any,
      companyPhone: any,
      csahier: any,
      customerName: any,
      customerPhone: any,
      customerSign: any,
      deliveryer: any,
      invoiceType: any,
      mainBusiness: any,
      orderMaker: any,
      page: any,
      receiver: any,
      settleStyle: any,
      totalAccount: any,
      totalAmountBig: any,
      totalAmountSmall: any,
      ydDeliveryDetails: [{
        orderNumber: any,
        partsNumber: any,
        partsName: any,
        vehicleType: any,
        productionAarea: any,
        unitPrice: any,
        quantity: any,
        account: any,
        comment: any,
        unit: any
      }],
      businessCode: any,
      deliverySign: any,
      handlerSign: any,
      totalAmount: any,
      totalQuantity: any,
      MxDeliveryDetails: [{
        styleNo: any,
        style: any,
        color: any,
        unit: any,
        modelS: any,
        modelM: any,
        modelL: any,
        subtotal: any,
        unitPrice: any,
        account: any,
        comment: any
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
    constructor(props: any, context: any) {
      super(props, context);
      this.state = {
        // 上传的文件列表, 无论上传还是失败，success字段会表示文件是否上传成功
        maxLength: 10,
        files: [],
        onLeave: any,
        url: 'http://localhost:8080/api/upload',
        cq: 10,
        onEnter: any,
        maxSize: 10240000,
        suffixs: [],
        onError: any,
        multiple: true,
        images: 'image/*',
        isDragover: false,
        filepath: any,
        display: 'none',
        title: any,
        deliverMessage: [],
        deliveryNo: any,
        address: any,
        contactNUmber: any,
        deliveryCompany: any,
        deliveryDate: any,
        handler: any,
        note: any,
        picker: any,
        deliveryDetails: [{
          batchNo: any,
          brand: any,
          comment: any,
          date: any,
          materialNo: any,
          quantity: any,
          singleWeight: any,
          storehouseNo: any,
          totalWeight: any,
          unit: any
        }],
        displayTwo: 'none',
        displayThree: 'none',
        adress: any,
        companyPhone: any,
        csahier: any,
        customerName: any,
        customerPhone: any,
        customerSign: any,
        deliveryer: any,
        invoiceType: any,
        mainBusiness: any,
        orderMaker: any,
        page: any,
        receiver: any,
        settleStyle: any,
        totalAccount: any,
        totalAmountBig: any,
        totalAmountSmall: any,
        ydDeliveryDetails: [{
          orderNumber: any,
          partsNumber: any,
          partsName: any,
          vehicleType: any,
          productionAarea: any,
          unitPrice: any,
          quantity: any,
          account: any,
          comment: any,
          unit: any
        }],
        businessCode: any,
        deliverySign: any,
        handlerSign: any,
        totalAmount: any,
        totalQuantity: any,
        MxDeliveryDetails: [{
          styleNo: any,
          style: any,
          color: any,
          unit: any,
          modelS: any,
          modelM: any,
          modelL: any,
          subtotal: any,
          unitPrice: any,
          account: any,
          comment: any
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
      this.inputRef = React.createRef();

      this.clockClick = this.clockClick.bind(this);
      // tslint:disable-next-line: unnecessary-bind
      this.handleFileChange = this.handleFileChange.bind(this);
      this.showError = this.showError.bind(this);
      this.downLoadError = this.downLoadError.bind(this);
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
          alert(errMsg);
        } else {
          files[i].guid = guid();
          this.addQueue(files[i]);
        }
      }
    }

    sumbit = async () => {
      const { onLeave, onError, url } = this.props;
      const arr: any [] = [];
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
            'http://localhost:8080/api/upload',
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
                 alert('您上传的文件有误，请再确认一下');
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

    addQueue = (file: any) => {
      this.queue = [...this.queue, file];
      this.processQueue();
    }

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
          files: prevState.files.filter(f => f.guid !== guid)
        };
      });
    }

    handleDownLoadClick = (filepath: any) => {
      // tslint:disable-next-line: no-console
      console.log(filepath);
      // tslint:disable-next-line: no-inferrable-types
      const filepaths: string = filepath;
      // tslint:disable-next-line: no-console
      console.log(filepaths);
      axios.get(
        'http://localhost:8080/api/download',
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
      .then(function(response) {
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
           alert('下载成功');
      })
      // tslint:disable-next-line: only-arrow-functions
      .catch(function(error) {
          alert('下载失败');
      });
    }

    handleShowClick = (filepath: any) => {
      const filepaths: string = filepath;
      axios.get(
        'http://localhost:8080/api/showDetails',
        {
          params: {
            filepath: filepaths
          }
        })
      // tslint:disable-next-line: only-arrow-functions
      .then((response: any) => {
         // tslint:disable-next-line: no-console
        console.log(response);
         // tslint:disable-next-line: no-inferrable-types
         const type = response.data.type;
          if (type === '1') {
            const titles = response.data.title;
            const deliverMessages = response.data.deliverMessage;
            const detaildeliveryNo = deliverMessages.deliveryNo;
            const detailaddress = deliverMessages.address;
            const detailcontactNUmber = deliverMessages.contactNUmber;
            const detaildeliveryCompany = deliverMessages.deliveryCompany;
            const detaildeliveryDate = deliverMessages.deliveryDate;
            const detailhandler = deliverMessages.handler;
            const detailnote = deliverMessages.note;
            const detailpicker = deliverMessages.picker;
            const deliveryDetailsArr = response.data.deliveryDetails;
            this.setState({
             display: 'block',
             title: titles,
             deliveryNo: detaildeliveryNo,
             address: detailaddress,
             contactNUmber: detailcontactNUmber,
             deliveryCompany: detaildeliveryCompany,
             deliveryDate: detaildeliveryDate,
             handler: detailhandler,
             note: detailnote,
             picker: detailpicker,
             deliveryDetails: deliveryDetailsArr
           });
            alert('成功');
          } else if (type === '2') {
            const titles = response.data.title;
            const mxDeliverMessage = response.data.mxDeliverMessage;
            const deaddress = mxDeliverMessage.address;
            const debusinessCode = mxDeliverMessage.businessCode;
            const dedeliveryDate = mxDeliverMessage.deliveryDate;
            const dedeliveryNo = mxDeliverMessage.deliveryNo;
            const dedeliverySign = mxDeliverMessage.deliverySign;
            const dehandlerSign = mxDeliverMessage.handlerSign;
            const denote = mxDeliverMessage.note;
            const deorderMaker = mxDeliverMessage.orderMaker;
            const detotalAmount = mxDeliverMessage.totalAmount;
            const detotalQuantity = mxDeliverMessage.totalQuantity;
            const deliveryDetailsArr = response.data.deliveryDetails;
            this.setState({
              displayTwo: 'block',
              title: titles,
              address: deaddress,
              businessCode: debusinessCode,
              deliveryDate: dedeliveryDate,
              deliveryNo: dedeliveryNo,
              deliverySign: dedeliverySign,
              handlerSign: dehandlerSign,
              note: denote,
              orderMaker: deorderMaker,
              totalAmount: detotalAmount,
              totalQuantity: detotalQuantity,
              MxDeliveryDetails: deliveryDetailsArr
            });
            alert('成功');
          } else if (type === '3') {
            const titles = response.data.title;
            const ydDeliverMessages = response.data.ydDeliverMessage;
            const detailadress = ydDeliverMessages.adress;
            const detailcompanyPhone = ydDeliverMessages.companyPhone;
            const detailcsahier = ydDeliverMessages.csahier;
            const detailcustomerName = ydDeliverMessages.customerName;
            const detailcustomerPhone = ydDeliverMessages.customerPhone;
            const detailcustomerSign = ydDeliverMessages.customerSign;
            const detaildeliveryDate = ydDeliverMessages.deliveryDate;
            const detaildeliveryNo = ydDeliverMessages.deliveryNo;
            const detaildeliveryer = ydDeliverMessages.deliveryer;
            const detailinvoiceType = ydDeliverMessages.invoiceType;
            const detailmainBusiness = ydDeliverMessages.mainBusiness;
            const detailorderMaker = ydDeliverMessages.orderMaker;
            const detailpage = ydDeliverMessages.page;
            const detailreceiver = ydDeliverMessages.receiver;
            const detailsettleStyle = ydDeliverMessages.settleStyle;
            const detailtotalAccount = ydDeliverMessages.totalAccount;
            const detailtotalAmountBig = ydDeliverMessages.totalAmountBig;
            const detailtotalAmountSmall = ydDeliverMessages.totalAmountSmall;
            const ydDeliveryDetailsArr = response.data.ydDeliveryDetails;
            this.setState({
              displayThree: 'block',
              title: titles,
              adress: detailadress,
              companyPhone: detailcompanyPhone,
              csahier: detailcsahier,
              customerName: detailcustomerName,
              customerPhone: detailcustomerPhone,
              customerSign: detailcustomerSign,
              deliveryer: detaildeliveryer,
              invoiceType: detailinvoiceType,
              mainBusiness: detailmainBusiness,
              orderMaker: detailorderMaker,
              page: detailpage,
              receiver: detailreceiver,
              settleStyle: detailsettleStyle,
              totalAccount: detailtotalAccount,
              totalAmountBig: detailtotalAmountBig,
              totalAmountSmall: detailtotalAmountSmall,
              deliveryDate: detaildeliveryDate,
              deliveryNo: detaildeliveryNo,
              ydDeliveryDetails: ydDeliveryDetailsArr
            });
            alert('成功');
          }

      })
      // tslint:disable-next-line: only-arrow-functions
      .catch(function(error) {
          alert('失败');
      });

    }
    clockClick(event: any) {
      this.setState({ display: 'none', displayTwo: 'none', displayThree: 'none' });
    }
    showError(event: any) {
       alert('上传模板有误无法显示，请确认模板');
    }
    downLoadError(event: any) {
      alert('上传模板有误无法下载，请确认模板');
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
              <li className="fourli">类型</li>
              <li className="thirdli">操作</li>
            </ul>
            {
                // tslint:disable-next-line: ter-arrow-body-style
               this.state.name.map((item, index) => {
                 // tslint:disable-next-line: no-console
                 console.log(this.state.name);
                  return(
                   // tslint:disable-next-line: jsx-key
                   <div style={{ display: this.state.namedisplay }} key={index}>
                      <span className="fileName">{item}</span>
                      <span className="state">上传中</span>
                      <span className="gengone">-</span>
                      <span className="gengtwo">-</span>
                   </div>
                  );
              })
            }

        { /* 上传列表 */ }
        {
          // tslint:disable-next-line: ter-arrow-body-style
          this.state.files.map(file => {
            return (<div className="allFile" key={file.guid}>
              <span className="fileName">{file.name}</span>
              {file.success ? <span className="state">成功</span> : <span className="state">失败/单据类型不匹配</span>}
              {file.success ? <span className="type">{file.templatetype}</span> : <span className="type">无</span>}
              {file.success ? <span className="displayshow" onClick={this.handleShowClick.bind(this, file.filepath)}>显示</span> :
                              <span className="displayshow" onClick={this.showError}>显示</span>}
              {file.success ? <span className="download" onClick={this.handleDownLoadClick.bind(this, file.filepath)}>下载</span> :
                              <span className="download" onClick={this.downLoadError}>下载</span>}
              <span className="del" onClick={this.handleCloseClick.bind(this, file.guid)}>删除</span>
            </div>
            );
          })
        }
        </div>
        <div className="formDiv">
        <form className = "upform" method = "post" action = "" encType = "multipart/form-data"
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
              <input ref={input => this.inputRef = input} onChange={this.handleFileChange} style = {{ 'display': 'none' }}
                                  type = "file" id = "file" multiple = {multiple} accept="image/*"/>
          <label className = "forlale" htmlFor = "file">
          <p className="changeContent">
          <span>请<span className="choosefile">选择文件</span><span>或</span><span className="choosefile" >拖拽</span>文件到这里</span>
          </p>
          </label>
      </form>
      </div>
      </div>
    <div className="popLayer" style = {{ display: this.state.display }}>
    <span className="close" onClick={this.clockClick}>关闭</span>
      <div className="popBox">
           <div className="title">{this.state.title}</div>
           <div className="leftcontent">
              <ul>
                <li>发货单位:{this.state.deliveryCompany}</li>
                <li>地址:{this.state.address}</li>
              </ul>
           </div>
           <div className="rightcontent">
             <ul>
                 <li>发货单号:{this.state.deliveryNo}</li>
                 <li>发货日期:{this.state.deliveryDate}</li>
                 <li>联系电话:{this.state.contactNUmber}</li>
             </ul>
           </div>
       <div className="firstdiv">
           <table>
                   <thead>
                       <tr>
                           <th>仓库</th>
                           <th>料号</th>
                           <th>品牌</th>
                           <th>单位</th>
                           <th>数量</th>
                           <th>单重</th>
                           <th>合计重量</th>
                           <th>批次号</th>
                           <th>出货日期</th>
                           <th>备注</th>
                       </tr>
                   </thead>
                   <tbody>
                     {
                       // tslint:disable-next-line: ter-arrow-body-style
                       this.state.deliveryDetails.map((details, index) => {
                           return(
                            // tslint:disable-next-line: jsx-key
                            <tr key={index}>
                                <td>{details.storehouseNo}</td>
                                <td>{details.materialNo}</td>
                                <td>{details.brand}</td>
                                <td>{details.unit}</td>
                                <td>{details.quantity}</td>
                                <td>{details.singleWeight}</td>
                                <td>{details.totalWeight}</td>
                                <td>{details.batchNo}</td>
                                <td>{details.date}</td>
                                <td>{details.comment}</td>
                           </tr>
                           );
                       })
                     }
                   </tbody>
               </table>
           <div className="onebottomcontent">{this.state.note}</div>
           <div>
              <span>经手人(签字或盖章){this.state.handler}</span>
              <span className="spantwo">领料人(签字或盖章){this.state.picker}</span>
           </div>
           </div>
           </div>
      </div>
      <div className="popLayer" style = {{ display: this.state.displayThree }}>
      <span className="close" onClick={this.clockClick}>关闭</span>
      <div className="popBox">
        <div className="title">{this.state.title}</div>
        <div className="leftcontent">
         <ul>
           <li>客户名称:{this.state.customerName}</li>
         <li>客户电话:{this.state.customerPhone}</li>
         </ul>
      </div>
      <div className="midcontent">
        <ul>
          <li>日期:{this.state.deliveryDate}</li>
          <li>发票种类:{this.state.invoiceType}</li>
        </ul>
      </div>
      <div className="rightcontent">
        <ul>
          <li>出库单号:{this.state.deliveryNo}</li>
          <li>结算方式:{this.state.settleStyle}</li>
        </ul>
      </div>
    <div className="firstdiv">
      <table>
          <thead>
            <tr>
              <th>序号</th>
              <th>配件编号</th>
              <th>配件名称</th>
              <th>车型</th>
              <th>产地</th>
              <th>单位</th>
              <th>单价</th>
              <th>数量</th>
              <th>金额</th>
              <th>备注</th>
            </tr>
          </thead>
          <tbody>
              {
                       // tslint:disable-next-line: ter-arrow-body-style
                       this.state.ydDeliveryDetails.map((details, index) => {
                           return(
                            // tslint:disable-next-line: jsx-key
                            <tr key={index}>
                            <td>{details.orderNumber}</td>
                            <td>{details.partsNumber}</td>
                            <td>{details.partsName}</td>
                            <td>{details.vehicleType}</td>
                            <td>{details.unit}</td>
                            <td>{details.partsNumber}</td>
                            <td>{details.unitPrice}</td>
                            <td>{details.quantity}</td>
                            <td>{details.account}</td>
                            <td>{details.comment}</td>
                          </tr>
                           );
                       })
                     }
          </tbody>
        </table>
      <div className="threebottomcontent">本页小计金额:{this.state.totalAccount}</div>
      <div className="threebottomcontent"><span>总合计金额(大写):{this.state.totalAmountBig}</span><span className="totalmoneysmall">总合计金额(小写):{this.state.totalAmountSmall}</span></div>
      <div>
         <span>公司电话:{this.state.totalAmountSmall}</span>
         <span className="spantwo">地址:{this.state.address}</span>
      </div>
      <div>
         <span>制单人:{this.state.orderMaker}</span>
         <span className="delivery">发货:{this.state.deliveryer}</span>
         <span className="delivery">收款:{this.state.csahier}</span>
         <span className="delivery">收货:{this.state.receiver}</span>
         <span className="delivery">客户签字:{this.state.customerSign}</span>
         <span className="delivery">页码:1/1</span>
      </div>
      <div>
         <span>主营:{this.state.mainBusiness}</span>
      </div>
      </div>
      </div>
    </div>
    <div className="popLayer" style = {{ display: this.state.displayTwo }}>
      <span className="close" onClick={this.clockClick}>关闭</span>
      <div className="popBox">
        <div className="title">{this.state.title}</div>
      <div><span className="sellorder">出货单</span><span className="page">页码: 1/1</span></div>
        <div className="leftcontent">
         <ul>
           <li>客户代码:{this.state.businessCode}</li>
         <li>地址:{this.state.address}</li>
         </ul>
      </div>
      <div className="rightcontent">
        <ul>
          <li>出货单号:{this.state.deliveryNo}</li>
          <li>出货日期:{this.state.deliveryDate}</li>
        </ul>
      </div>
    <div className="firstdiv">
      <table>
          <thead>
            <tr>
              <th>款号</th>
              <th>款式</th>
              <th>颜色</th>
              <th>单位</th>
              <th>S</th>
              <th>M</th>
              <th>L</th>
              <th>小计</th>
              <th>单价</th>
              <th>金额</th>
              <th>备注</th>
            </tr>
          </thead>
          <tbody>
              {
                       // tslint:disable-next-line: ter-arrow-body-style
                       this.state.MxDeliveryDetails.map((details, index) => {
                           return(
                            // tslint:disable-next-line: jsx-key
                            <tr key={index}>
                                  <td>{details.styleNo}</td>
                                  <td>{details.style}</td>
                                  <td>{details.color}</td>
                                  <td>{details.unit}</td>
                                  <td>{details.modelS}</td>
                                  <td>{details.modelM}</td>
                                  <td>{details.modelL}</td>
                                  <td>{details.subtotal}</td>
                                  <td>{details.unitPrice}</td>
                                  <td>{details.account}</td>
                                  <td>{details.comment}</td>
                          </tr>
                           );
                       })
                     }
          </tbody>
        </table>
      <div className="bottomcontent">
        <span className="totalnum">合计数量:{this.state.totalQuantity}</span>
        <span className="totalmoney">合计金额:{this.state.totalAmount}</span>
      </div>
      <div>收到货后,请立即验货,货物如有问题,请于一星期内通知,逾期本公司恕不负责.</div>
      <div>
        <span>收货人签名:{this.state.deliverySign}</span>
        <span className="sig">送货人签名:{this.state.handlerSign}</span>
        <span className="sig">制单人:{this.state.orderMaker}</span>
      </div>
      </div>
      </div>
    </div>
    <div>
      <div className="ajax-loading" id="ajaxLoading" style={{ display: this.state.loading }}>
         <div className="overlay">&nbsp;</div>
         <div className="loading">
         <img className="loadimg" src="../content/images/load.gif" alt=""/>
         <span className="laodspan" >文件解析中，请稍后...</span>
         </div>
      </div>
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
    images: PropTypes.string,
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
    cq: 10,
    multiple: true,
    images: 'image/*',
    maxSize: 1024,
    maxLength: 10,
    suffixs: []
  };
  export default Upload;
