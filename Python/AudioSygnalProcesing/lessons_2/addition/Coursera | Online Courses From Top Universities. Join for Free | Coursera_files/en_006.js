webpackJsonp([41],{"+VWQ":function(module,exports){exports.default={"es":true,"fr":true,"pt":true,"ru":true,"zh":true,"zh-hk":"zh-tw","zh-mo":"zh-tw","zh-tw":true}},"2FFM":function(module,exports,e){var t=e("+VWQ"),r=t.default?t.default:{},o=e("zQM/"),n=o(r);n.getLocale=function(){return"en"},module.exports=n},"7eO6":function(module,exports){},AWtJ:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("LwZ5"),e("oq/K")],void 0!==(r=function(require,exports,module){"use strict";function updateSupplementProgress(e){var r=e.itemId,o=e.courseId,n=e.userId,s=e.api,i={data:{userId:n,courseId:o,itemId:r}};return t(s.post("",i))}var t=e("go2y"),r=e("LwZ5"),o=e("oq/K"),n=r(o.supplementCompletionApi,{type:"rest"}),s=r(o.supplementStartApi,{type:"rest"}),i={markComplete:function markComplete(e,t,r){return updateSupplementProgress({itemId:e,courseId:t,userId:r,api:n})},markStarted:function markStarted(e,t,r){return updateSupplementProgress({itemId:e,courseId:t,userId:r,api:s})}};module.exports=i}.apply(exports,t))&&(module.exports=r)},KOoj:function(module,exports){},MHr7:function(module,exports,e){(function(t){var r,o;r=[e,exports,module,e("U7vG"),e("9eZc"),e("oq/K"),e("rMvO"),e("AWtJ"),e("2FFM"),e("llfQ")],void 0!==(o=function(require,exports,module){"use strict";function _defaults(e,t){for(var r=Object.getOwnPropertyNames(t),o=0;o<r.length;o++){var n=r[o],s=Object.getOwnPropertyDescriptor(t,n);s&&s.configurable&&void 0===e[n]&&Object.defineProperty(e,n,s)}return e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _possibleConstructorReturn(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function _inherits(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):_defaults(e,t))}var t,r,o=e("U7vG"),n=e("9eZc"),s=e("oq/K"),i=e("rMvO"),a=i.updateProgress,c=e("AWtJ"),p=e("2FFM");e("llfQ");var u=(r=t=function(e){function ReadingCompleteButton(){var t,r,o;_classCallCheck(this,ReadingCompleteButton);for(var n=arguments.length,i=Array(n),p=0;p<n;p++)i[p]=arguments[p];return t=r=_possibleConstructorReturn(this,e.call.apply(e,[this].concat(i))),r.markComplete=function(){var e=r.props,t=e.itemId,o=e.courseId,n=e.userId;c.markComplete(t,o,n).then(r.refreshProgress).done()},r.refreshProgress=function(){var e=r.props,t=e.courseProgress,o=e.itemId;t.getItemProgress(o).setState(s.progressCompleted,{refreshCourseProgress:!0}),r.context.executeAction(a,{courseProgress:t})},o=t,_possibleConstructorReturn(r,o)}return _inherits(ReadingCompleteButton,e),ReadingCompleteButton.prototype.render=function render(){return o.createElement("div",{className:"rc-ReadingCompleteButton horizontal-box align-items-right"},this.props.isComplete?o.createElement("div",{className:"completed"},o.createElement(n,{name:"checkmark",className:"color-success-dark"})," ",p("Complete")):o.createElement("button",{className:"primary mark-complete",onClick:this.markComplete},p("Mark as completed")))},ReadingCompleteButton}(o.Component),t.propTypes={isComplete:o.PropTypes.bool,itemId:o.PropTypes.string,courseId:o.PropTypes.string,userId:o.PropTypes.number,courseProgress:o.PropTypes.object},t.contextTypes={executeAction:o.PropTypes.func.isRequired},r);module.exports=u}.apply(exports,r))&&(module.exports=o)}).call(exports,e("U7vG"))},Ux9U:function(module,exports,e){(function(t){var r,o;r=[e,exports,module,e("U7vG"),e("MhD4"),e("bwuv"),e("H1MK"),e("/gQH"),e("RemH"),e("qNyB"),e("aSig"),e("PRpN"),e("vPxY"),e("zgJe")],void 0!==(o=function(require,exports,module){"use strict";function _defaults(e,t){for(var r=Object.getOwnPropertyNames(t),o=0;o<r.length;o++){var n=r[o],s=Object.getOwnPropertyDescriptor(t,n);s&&s.configurable&&void 0===e[n]&&Object.defineProperty(e,n,s)}return e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _possibleConstructorReturn(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function _inherits(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):_defaults(e,t))}var t,r,o=e("U7vG"),n=e("MhD4"),s=e("bwuv"),i=e("H1MK"),a=e("/gQH"),c=e("RemH"),p=e("qNyB"),u=e("aSig"),l=e("PRpN"),f=e("vPxY");e("zgJe");var d=(r=t=function(e){function ReadingItem(){return _classCallCheck(this,ReadingItem),_possibleConstructorReturn(this,e.apply(this,arguments))}return _inherits(ReadingItem,e),ReadingItem.prototype.componentDidMount=function componentDidMount(){var e=this.props.itemMetadata;n.setCommon({title:e.getName()+" | Coursera",description:e.get("lesson.module.description"),pageHref:window.location.href})},ReadingItem.prototype.render=function render(){var e=this.props,t=e.isComponentLoaded,r=e.itemMetadata,n=e.readingCml;return o.createElement("div",{className:"rc-ReadingItem"},o.createElement(i,{itemMetadata:r,itemType:c.Reading,showFeedback:t,isCard:!0},t?o.createElement(s,{itemMetadata:r,readingCml:n}):o.createElement(a,null)))},ReadingItem}(o.Component),t.propTypes={isComponentLoaded:o.PropTypes.bool.isRequired,itemMetadata:o.PropTypes.instanceOf(f).isRequired,readingCml:o.PropTypes.object},r),m=u(d,function(e,t){return l(e.itemMetadata).then(function(e){return{readingCml:e}})});module.exports=p(m)}.apply(exports,r))&&(module.exports=o)}).call(exports,e("U7vG"))},aSig:function(module,exports,e){(function(t){var r,o;r=[e,exports,module,e("U7vG")],void 0!==(o=function(require,exports,module){"use strict";function _defaults(e,t){for(var r=Object.getOwnPropertyNames(t),o=0;o<r.length;o++){var n=r[o],s=Object.getOwnPropertyDescriptor(t,n);s&&s.configurable&&void 0===e[n]&&Object.defineProperty(e,n,s)}return e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _possibleConstructorReturn(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function _inherits(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):_defaults(e,t))}var t=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var o in r)Object.prototype.hasOwnProperty.call(r,o)&&(e[o]=r[o])}return e},r=e("U7vG"),o=function componentDataFetcher(e,o){var n,s,i=e.displayName||e.name,a=(s=n=function(n){function DataFetcherComponent(){var e,t,r;_classCallCheck(this,DataFetcherComponent);for(var o=arguments.length,s=Array(o),a=0;a<o;a++)s[a]=arguments[a];return e=t=_possibleConstructorReturn(this,n.call.apply(n,[this].concat(s))),t.displayName=i+"DataFetcher",t.state={isComponentLoaded:!1},r=e,_possibleConstructorReturn(t,r)}return _inherits(DataFetcherComponent,n),DataFetcherComponent.prototype.componentDidMount=function componentDidMount(){var e=this,r=this.props,n=this.context,s=n.router,i=o(r,s.params,n).then(function(r){e.setState(t({isComponentLoaded:!0},r))});i.catch&&i.catch(function(e){throw e}),i.done&&i.done()},DataFetcherComponent.prototype.render=function render(){return r.createElement(e,t({},this.props,this.state))},DataFetcherComponent}(r.Component),n.contextTypes={executeAction:r.PropTypes.func,router:r.PropTypes.object},s);return a.BaseComp=e,a};module.exports=o}.apply(exports,r))&&(module.exports=o)}).call(exports,e("U7vG"))},bwuv:function(module,exports,e){(function(t){var r,o;r=[e,exports,module,e("U7vG"),e("JZCc"),e("rImi"),e("MHr7"),e("AWtJ"),e("H+ns"),e("vPxY")],void 0!==(o=function(require,exports,module){"use strict";function _defaults(e,t){for(var r=Object.getOwnPropertyNames(t),o=0;o<r.length;o++){var n=r[o],s=Object.getOwnPropertyDescriptor(t,n);s&&s.configurable&&void 0===e[n]&&Object.defineProperty(e,n,s)}return e}function _defineProperty(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _possibleConstructorReturn(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function _inherits(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):_defaults(e,t))}var t,r,o=e("U7vG"),n=e("JZCc"),s=e("rImi"),i=e("MHr7"),a=e("AWtJ"),c=e("H+ns"),p=e("vPxY"),u=(r=t=function(e){function Reading(){return _classCallCheck(this,Reading),_possibleConstructorReturn(this,e.apply(this,arguments))}return _inherits(Reading,e),Reading.prototype.componentDidMount=function componentDidMount(){var e=this.props,t=e.itemMetadata,r=e.courseId,o=e.userId;a.markStarted(t.getId(),r,o)},Reading.prototype.render=function render(){var e,t=s.NAME,r=s.USER_ID,a=this.props,c=a.itemMetadata,p=a.courseId,u=a.userId,l=a.fullName,f=a.isComplete,d=a.courseProgress,m=a.readingCml;return o.createElement("div",null,o.createElement(n,{cml:m,className:"rc-Reading",shouldApplyTracking:!0,variableData:(e={},_defineProperty(e,t,l),_defineProperty(e,r,u),e)}),o.createElement(i,{userId:u,courseId:p,isComplete:f,itemId:c.getId(),courseProgress:d}))},Reading}(o.Component),t.propTypes={itemMetadata:o.PropTypes.instanceOf(p).isRequired,isComplete:o.PropTypes.bool.isRequired,readingCml:o.PropTypes.object.isRequired,courseProgress:o.PropTypes.object.isRequired,courseId:o.PropTypes.string,userId:o.PropTypes.number,fullName:o.PropTypes.string},t.contextTypes={executeAction:o.PropTypes.func.isRequired},r);module.exports=c(u,["ProgressStore","CourseStore","ApplicationStore"],function(e,t){var r=e.ProgressStore,o=e.CourseStore,n=e.ApplicationStore;return{courseProgress:r.courseProgress,isComplete:r.isItemComplete(t.itemMetadata),courseId:o.getCourseId(),userId:n.getUserData().id,fullName:n.getUserData().fullName}})}.apply(exports,r))&&(module.exports=o)}).call(exports,e("U7vG"))},llfQ:function(module,exports,e){var t=e("KOoj");"string"==typeof t&&(t=[[module.i,t,""]]);var r,o={};o.transform=void 0;var n=e("MTIv")(t,o);t.locals&&(module.exports=t.locals)},rImi:function(module,exports,e){var t,r;t=[e,exports,module],void 0!==(r=function(require,exports,module){"use strict";module.exports={NAME:"NAME",USER_ID:"USER_ID",HASHED_USER_ID:"HASHED_USER_ID"}}.apply(exports,t))&&(module.exports=r)},zgJe:function(module,exports,e){var t=e("7eO6");"string"==typeof t&&(t=[[module.i,t,""]]);var r,o={};o.transform=void 0;var n=e("MTIv")(t,o);t.locals&&(module.exports=t.locals)}});