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
  displayFour: any;
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
  mitsubishiName: any;
  mitsubishiCompanyName: any;
  mitsubishiTelphone: any;
  mitsubishiEmail: any;
  questionOne: any;
  questionTwo: any;
  questionThree: any;
  questionFour: any;
  questionFive: any;
  questionSix: any;
  questionSeven: any;
  questionEight: any;
  questionNine: any;
  questionTen: any;
  mitsubishiComment: any;
  loading: any;
  name: any[];
  namedisplay: any;
  documenttype: any;
}
class Upload extends React.Component<any, ImgProps, []> {
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
      displayFour: any,
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
      mitsubishiName: any,
      mitsubishiCompanyName: any,
      mitsubishiTelphone: any,
      mitsubishiEmail: any,
      questionOne: any,
      questionTwo: any,
      questionThree: any,
      questionFour: any,
      questionFive: any,
      questionSix: any,
      questionSeven: any,
      questionEight: any,
      questionNine: any,
      questionTen: any,
      mitsubishiComment: any,
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
      url: 'http://localhost:8080/api/ocr/iocr/upload',
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
      displayFour: 'none',
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
      mitsubishiName: any,
      mitsubishiCompanyName: any,
      mitsubishiTelphone: any,
      mitsubishiEmail: any,
      questionOne: any,
      questionTwo: any,
      questionThree: any,
      questionFour: any,
      questionFive: any,
      questionSix: any,
      questionSeven: any,
      questionEight: any,
      questionNine: any,
      questionTen: any,
      mitsubishiComment: any,
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
          'http://localhost:8080/api/ocr/iocr/upload',
          uploadFile
        ).then((_: any) => {
          // tslint:disable-next-line: no-console
          console.log(_);
          const filepathType = _.data.type;
          // tslint:disable-next-line: no-console
          console.log(filepathType);
          const message = _.data.errorMessage;
          // tslint:disable-next-line: no-console
          console.log(message);
          const templatetype = _.data.templateType;
          if (filepathType !== null && filepathType !== undefined && filepathType !== '') {
            // tslint:disable-next-line: no-console
            console.log(filepathType);
            this.setState(prevState => {
              current.success = true;
              current.filepathType = filepathType;
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
              current.filepathType = filepathType;
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
  handleDownLoadClick = (filepathType: any) => {
    // tslint:disable-next-line: no-console
    console.log(filepathType);
    // tslint:disable-next-line: no-inferrable-types
    const filepathTypes: string = filepathType;
    // tslint:disable-next-line: no-console
    console.log(filepathTypes);
    axios.get(
      'http://localhost:8080/api/ocr/iocr/download',
      {
        params: {
          filepathType: filepathTypes
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
  handleShowClick = (filepathType: any) => {
    const filepathTypes: string = filepathType;
    axios.get(
      'http://localhost:8080/api/ocr/iocr/showDetails',
      {
        params: {
          filepathType: filepathTypes
        }
      })
      // tslint:disable-next-line: only-arrow-functions
      .then((response: any) => {
        // tslint:disable-next-line: no-console
        console.log(response);
        // tslint:disable-next-line: no-inferrable-types
        // 根据后台返回数据 用type判断用哪个模板
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
        } else if (type === '4') {
          const titles = response.data.title;
          const mitsubishiNames = response.data.mitsubishiName;
          const mitsubishiCompanyNames = response.data.mitsubishiCompanyName;
          const mitsubishiTelphones = response.data.mitsubishiTelphone;
          const mitsubishiEmails = response.data.mitsubishiEmail;
          const questionOnes = response.data.questionOne;
          const questionTwos = response.data.questionTwo;
          const questionThrees = response.data.questionThree;
          const questionFours = response.data.questionFour;
          const questionFives = response.data.questionFive;
          const questionSixs = response.data.questionSix;
          const questionSevens = response.data.questionSeven;
          const questionEights = response.data.questionEight;
          const questionNines = response.data.questionNine;
          const questionTens = response.data.questionTen;
          const mitsubishiComments = response.data.mitsubishiComment;
          this.setState({
            displayFour: 'block',
            title: titles,
            mitsubishiName: mitsubishiNames,
            mitsubishiCompanyName: mitsubishiCompanyNames,
            mitsubishiTelphone: mitsubishiTelphones,
            mitsubishiEmail: mitsubishiEmails,
            questionOne: questionOnes,
            questionTwo: questionTwos,
            questionThree: questionThrees,
            questionFour: questionFours,
            questionFive: questionFives,
            questionSix: questionSixs,
            questionSeven: questionSevens,
            questionEight: questionEights,
            questionNine: questionNines,
            mitsubishiComment: mitsubishiComments
          });
        }

      })
      // tslint:disable-next-line: only-arrow-functions
      .catch(function (error) {
      });

  }
  //  详情关闭
  clockClick(event: any) {
    this.setState({ display: 'none', displayTwo: 'none', displayThree: 'none', displayFour: 'none' });
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
            <div className="overone">
              {
                // tslint:disable-next-line: ter-arrow-body-style
                this.state.files.map(file => {
                  return (
                    // tslint:disable-next-line: jsx-key
                    <div className="allFile" key={file.guid}>
                      <span className="fileName">{file.name}</span>
                      {file.success ? <span className="state">成功</span> : <span>失败/模板类型未定义</span>}
                      {file.success ? <span className="type">{file.templatetype}</span> : <span className="newtype">无</span>}
                      {file.success ? <span className="displayshow" onClick={this.handleShowClick.bind(this, file.filepathType)}>查看</span> :
                        <span className="newdisplayshow">查看</span>}
                      {file.success ? <span className="download" onClick={this.handleDownLoadClick.bind(this, file.filepathType)}>下载</span> :
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
        { /* 模板1 */}
        <div className="popLayer" style={{ display: this.state.display }}>
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
                      return (
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
        { /* 模板2 */}
        <div className="popLayer" style={{ display: this.state.displayTwo }}>
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
                      return (
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
        { /* 模板3 */}
        <div className="popLayer" style={{ display: this.state.displayThree }}>
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
                      return (
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
              <div className="threebottomcontent"><span>总合计金额(大写):{this.state.totalAmountBig}</span>
                <span className="totalmoneysmall">总合计金额(小写):{this.state.totalAmountSmall}</span>
              </div>
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
        { /* 模板4 */}
        <div className="popLayer" style={{ display: this.state.displayFour }}>
          <span className="close" onClick={this.clockClick}>关闭</span>
          <div className="popBox">
            <div className="title">三菱重工MGS-CN产品市场调查问卷</div>
            <div className="topContent">
              <ul>
                <li>姓名:{this.state.mitsubishiName}</li>
                <li>公司名称:{this.state.mitsubishiCompanyName}</li>
                <li>电话:{this.state.mitsubishiTelphone}</li>
                <li>E-mail:{this.state.mitsubishiEmail}</li>
              </ul>
            </div>
            <div className="mainContent">
              <ul>
                <li>问题1：柴油发电机选型的时候以哪种功率定义为标准(多选)  回答(请填写字母)：{this.state.questionOne}</li>
                <li>选项：A: PRP  B: COP  C: DCP  D: ESP  E: 其他</li>
                <li>问题2：基于上述功率定义选择,中国市场主流柴油发电机功率范围(多选)  回答(请填写字母)：{this.state.questionTwo}</li>
                <li>选项：A: 1800kw  B: 2000kw  C: 2200kw  D: 2400kw  E: 其他</li>
                <li>问题3：预测今后中国数据中心应用的柴油发电机主流安装方式  回答(请填写字母)：{this.state.questionThree}</li>
                <li>选项：A: 集装箱式，室外  B: 开放式，室内  C: 其他</li>
                <li>问题4：柴油发电机选择条件的先后优先度(排序)  回答(请填写字母)：{this.state.questionFour}</li>
                <li>选项：A: 喷油方式  B: 质量好，维护方便  C: 价格 D: 品牌 E: 其他</li>
                <li>问题5：下述规格中必要配置(多选)  回答(请填写字母)：{this.state.questionFive}</li>
                <li>选项：A: Dual Start  B: 原厂并机系统  C: 远程监控系统 D: 双轴承发电机  E: 其他</li>
                <li>问题6：数据中心常见负载功率因数  回答(请填写字母)：{this.state.questionSix}</li>
                <li>选项：A: 容性超前0.95  B: 容性超前0.9  C: 阻性1.0  D: 感性滞后0.8   E: 其他</li>
                <li>问题7：是否要求发电机组一步带载100%  回答(请填写字母)：{this.state.questionSeven}</li>
                <li>选项：A: 是  B: 否</li>
                <li>问题8：下述哪些条件是必须的  回答(请填写字母)：{this.state.questionEight}</li>
                <li>选项：A: 泰尔认证  B: Uptime认证  C: 国三排放  D: 其他</li>
                <li>问题9：通常希望发电机组的交货周期是多长时间  回答(请填写字母)：{this.state.questionNine}</li>
                <li>选项：A: 2个月  B: 3个月  C: 4个月以上也可以  D: 其他</li>
                <li>问题10是否使用过或者了解过三菱重工的柴油发电机组或者发动机产品  回答(请填写字母)：{this.state.questionTen}</li>
                <li>选项：A: 是  B: 否</li>
              </ul>
            </div>
            <div className="footContent">
              <ul>
                <li>请对三菱重工MGSCN产品提出宝贵意见(请使用正楷书写)</li>
                <li>{this.state.mitsubishiComment}</li>
              </ul>
            </div>

          </div>
        </div>
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
// 设置默认值
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
  cq: 10, // 限制上传数量
  multiple: true, // 是否开启多个上传 true 是 false 否
  images: 'image/*',
  maxSize: 1024 * 1024 * 1024,
  maxLength: 100,
  suffixs: []
};
export default Upload;
