webpackJsonp([27],{"+kzj":function(module,exports,e){(function(t){var r,n;r=[e,exports,module,e("U7vG"),e("AwHF"),e("8k/k"),e("9FwV"),e("MBY5"),e("llyE"),e("jOkS"),e("Lqnx"),e("PtSL"),e("rMvO"),e("CfcV"),e("BALM"),e("5Qp/"),e("Ivv8"),e("vC4y"),e("U97Q"),e("nVTL"),e("4wAy"),e("cjZO"),e("qDvH"),e("iomd"),e("w5FR"),e("lDcb"),e("KxE+"),e("4ivr"),e("6vDQ"),e("7hZU"),e("p4Of")],void 0!==(n=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}function _interopRequireWildcard(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&(t[r]=e[r]);return t.default=e,t}function _defaults(e,t){for(var r=Object.getOwnPropertyNames(t),n=0;n<r.length;n++){var o=r[n],u=Object.getOwnPropertyDescriptor(t,o);u&&u.configurable&&void 0===e[o]&&Object.defineProperty(e,o,u)}return e}function _classCallCheck(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function _possibleConstructorReturn(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function _inherits(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):_defaults(e,t))}var t,r,n=e("U7vG"),o=_interopRequireWildcard(n),u=e("AwHF"),i=_interopRequireDefault(u),s=e("8k/k"),a=_interopRequireDefault(s),d=e("9FwV"),c=_interopRequireDefault(d),l=e("MBY5"),f=_interopRequireDefault(l),p=e("llyE"),v=_interopRequireDefault(p),g=e("jOkS"),S=_interopRequireDefault(g),D=e("Lqnx"),h=_interopRequireDefault(D),m=e("PtSL"),y=_interopRequireDefault(m),I=e("rMvO"),R=e("CfcV"),C=e("BALM"),E=e("5Qp/"),L=e("Ivv8"),O=e("vC4y"),A=e("U97Q"),w=e("nVTL"),P=e("4wAy"),x=e("cjZO"),q=e("qDvH"),b=e("iomd"),k=e("w5FR"),M=_interopRequireDefault(k),H=e("lDcb"),G=_interopRequireDefault(H),F=e("KxE+"),T=_interopRequireDefault(F),U=e("4ivr"),Q=_interopRequireDefault(U),j=e("6vDQ"),B=_interopRequireDefault(j),V=e("7hZU"),Z=_interopRequireDefault(V),N=e("p4Of"),W=_interopRequireDefault(N),z=(r=t=function(e){function LegacyDataFetch(){return _classCallCheck(this,LegacyDataFetch),_possibleConstructorReturn(this,e.apply(this,arguments))}return _inherits(LegacyDataFetch,e),LegacyDataFetch.prototype.render=function render(){var e=this.props.children;if(!e)return null;return o.cloneElement(e,{})},LegacyDataFetch}(o.Component),t.contextTypes={router:o.PropTypes.object.isRequired,executeAction:o.PropTypes.func.isRequired,getStore:o.PropTypes.func.isRequired},r);module.exports=i.default.compose((0,S.default)(function(){return!1}),(0,v.default)(function(e,t){return{courseSlug:e.params.courseSlug}}),(0,f.default)([T.default,G.default,Z.default,M.default,Q.default,B.default],function(e,t,r,n,o,u){return{s12n:n.getS12n(),course:t.getMetadata(),courseId:t.getCourseId(),isEnrolled:r.isEnrolled(),sessionId:e.getSessionId(),isEnrolledInSession:e.isEnrolled(),s12nStoreHasLoaded:n.hasLoaded(),courseStoreHasLoaded:t.hasLoaded(),sessionStoreHasLoaded:e.hasLoaded(),verificationStoreHasLoaded:o.hasLoaded(),courseMembershipStoreHasLoaded:r.hasLoaded(),computedModelStoreHasLoaded:u.hasLoaded(),courseIdentifiersHaveLoaded:t.haveCourseIdentifiersLoaded()}}),(0,h.default)(function(e,t){var r=t.courseSlug;e.executeAction(y.default,r)}),(0,c.default)(function(e){return e.courseIdentifiersHaveLoaded}),(0,c.default)(function(e){return!!e.courseId},o.createElement(W.default,null)),(0,h.default)(function(e,t){var r=t.courseId;e.executeAction(O.loadMembership,r)}),(0,c.default)(function(e){return e.courseMembershipStoreHasLoaded}),(0,c.default)(function(e){var t=e.isEnrolled;return a.default.isSuperuser()||t},o.createElement(W.default,null)),(0,h.default)(function(e,t){var r=t.courseSlug,n=t.courseId;e.executeAction(E.loadComputedModels,{courseSlug:r,courseId:n})}),(0,c.default)(function(e){return e.computedModelStoreHasLoaded}),(0,h.default)(function(e,t){var r=t.courseId,n=t.courseSlug,o=a.default.get().id,u=a.default.isAuthenticatedUser();e.executeAction(R.getCurrentSession,{courseSlug:n}),e.executeAction(w.loadCourseViewGrade,{courseId:r}),e.executeAction(x.loadUserGroupsForCourse,{courseId:r,userId:o}),e.executeAction(b.loadHonorsUserPreferences,{authenticated:u}),e.executeAction(L.loadCertificateData,{courseId:r,userId:o}),e.executeAction(I.getProgress,{authenticated:u,courseId:r,userId:o}),e.executeAction(C.loadInsightsByContextWithProgress,{contextType:"COURSE",contextId:r})}),(0,c.default)(function(e){var t=e.s12nStoreHasLoaded,r=e.courseStoreHasLoaded,n=e.sessionStoreHasLoaded;return t&&r&&n}),(0,h.default)(function(e,t){var r=t.courseId,n=t.course,o=t.s12n,u=t.sessionId,i=a.default.get().id,s=a.default.isAuthenticatedUser(),d=o&&o.getId(),c=n.isVerificationEnabled(),l=e.getStore("CourseStore");e.executeAction(A.loadCourseDeadlines,{userId:i}),l.isCumulativeGradePolicy()&&e.executeAction(q.loadCoursePresentGrade,{userId:i,courseId:r}),e.executeAction(P.loadVerificationDisplay,{authenticated:s,userId:i,courseId:r,isCourseVerificationEnabled:c,s12nId:d}),e.executeAction(x.loadUserSessionGroupForCourse,{courseId:r,userId:i,sessionId:u})}),(0,c.default)(function(e){return e.verificationStoreHasLoaded}))(z)}.apply(exports,r))&&(module.exports=n)}).call(exports,e("U7vG"))},"37IR":function(module,exports,e){var t,r;t=[e,exports,module,e("s77z"),e("dpgV"),e("41Df")],void 0!==(r=function(require,exports,module){"use strict";var t=e("s77z"),r=e("dpgV"),n=e("41Df");module.exports=function(e){return t(n.build(r.prototype.resourceName,e))}}.apply(exports,t))&&(module.exports=r)},"5Qp/":function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("narh"),e("eoPD"),e("WXJF"),e("mv2b"),e("eoZB"),e("O88V"),e("6vDQ")],void 0!==(r=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var t=e("go2y"),r=_interopRequireDefault(t),n=e("narh"),o=_interopRequireDefault(n),u=e("eoPD"),i=_interopRequireDefault(u),s=e("WXJF"),a=_interopRequireDefault(s),d=e("mv2b"),c=_interopRequireDefault(d),l=e("eoZB"),f=_interopRequireDefault(l),p=e("O88V"),v=_interopRequireDefault(p),g=e("6vDQ"),S=_interopRequireDefault(g);exports.loadComputedModels=function(e,t){var n=t.courseSlug,u=t.courseId;if(e.getStore(S.default).hasLoaded())return(0,r.default)();return r.default.all([(0,i.default)(e),(0,o.default)(e,u),(0,c.default)(e,n),(0,a.default)(e,u),(0,f.default)(e,u),(0,v.default)(e,u)]).then(function(){e.dispatch("LOAD_COMPUTED_MODELS")})}}.apply(exports,t))&&(module.exports=r)},BALM:function(module,exports,e){var t,r;t=[e,exports,module,e("el12")],void 0!==(r=function(require,exports,module){"use strict";var t=e("el12");exports.loadInsightsByContextWithProgress=function(e,r){var n=r.contextType,o=r.contextId;t.loadInsightsByContextWithProgress(n,o).then(function(t){var r=t.elements;e.dispatch("LEARNER_LOAD_INSIGHTS",{insights:r})}).done()}}.apply(exports,t))&&(module.exports=r)},CIu2:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("iFGm"),e("LwZ5"),e("8k/k")],void 0!==(r=function(require,exports,module){"use strict";var t=e("go2y"),r=e("iFGm"),n=e("LwZ5"),o=e("8k/k"),u=n("/api/onDemandHomeProgress.v1",{type:"rest"});exports.getHomeProgress=function(e){var n=o.get().id+"~"+e,i=new r(n).addQueryParam("fields","modulesCompleted,modulesPassed");return t(u.get(i.toString()))}}.apply(exports,t))&&(module.exports=r)},Ivv8:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("rB0C")],void 0!==(r=function(require,exports,module){"use strict";var t=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},r=e("go2y"),n=e("rB0C"),o={showHidden:!0,fields:["courseId","grade"],includes:{vcMembership:{fields:["certificateCode","certificateCodeWithGrade","grade","grantedAt"]},course:{fields:[]}}};exports.loadCertificateData=function(e,u){var i=u.courseId,s=u.userId;if(e.getStore("CertificateStore").hasLoaded())return r();var a=void 0;return a=s?n(t({id:s+"~"+i},o,{rawData:!0})).then(function(t){e.dispatch("LOAD_MEMBERSHIPS",t)}):r().then(function(){e.dispatch("LOAD_MEMBERSHIPS",null)}),a.done(),a}}.apply(exports,t))&&(module.exports=r)},O88V:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("8k/k"),e("24lr"),e("CIu2")],void 0!==(r=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var t=e("go2y"),r=_interopRequireDefault(t),n=e("8k/k"),o=_interopRequireDefault(n),u=e("24lr"),i=_interopRequireDefault(u),s=e("CIu2");module.exports=function(e,t){if(e.getStore(i.default).hasLoaded())return(0,r.default)();return o.default.isAuthenticatedUser()?(0,s.getHomeProgress)(t).then(function(t){t.elements&&t.elements.length&&e.dispatch("LOAD_HOME_PROGRESS",t.elements[0])}).fail(function(){e.dispatch("LOAD_HOME_PROGRESS",{modulesCompleted:[],modulesPassed:[]})}):(e.dispatch("LOAD_HOME_PROGRESS",{modulesCompleted:[],modulesPassed:[]}),(0,r.default)())}}.apply(exports,t))&&(module.exports=r)},PtSL:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("X2hd"),e("lDcb")],void 0!==(r=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var t=e("go2y"),r=_interopRequireDefault(t),n=e("X2hd"),o=_interopRequireDefault(n),u=e("lDcb"),i=_interopRequireDefault(u);module.exports=function(e,t){if(e.getStore(i.default).haveCourseIdentifiersLoaded())return(0,r.default)();if(!t)throw new Error("Missing courseSlug");return(0,o.default)(t).then(function(r){var n=r.courseId,o=r.courseCertificates;if(!n)throw new Error("Missing courseId");return e.dispatch("SET_COURSE_IDENTIFIERS",{courseId:n,courseSlug:t,courseCertificates:o}),{courseId:n,courseSlug:t,courseCertificates:o}}).catch(function(n){console.error("Error getting courseId and courseCertificates from courseSlug: "+t+": ",n,n.stack);var o="",u=[];return e.dispatch("SET_COURSE_IDENTIFIERS",{courseId:"",courseSlug:t,courseCertificates:u}),(0,r.default)({courseId:"",courseSlug:t,courseCertificates:u})})}}.apply(exports,t))&&(module.exports=r)},U97Q:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("CoQL"),e("k+WV"),e("WXze"),e("AzSd"),e("SRBf")],void 0!==(r=function(require,exports,module){"use strict";var t=e("go2y"),r=e("CoQL"),n=e("k+WV"),o=e("WXze"),u=e("AzSd"),i=e("SRBf"),s=i.getCurrentWeek;exports.receiveDeadlines=function(e,t){var r=t.deadlines,n=r.isEnabled,o=r.moduleDeadlines;n?e.dispatch("LOAD_COURSE_DEADLINES",{moduleDeadlines:o}):e.dispatch("DISABLE_DEADLINES")},exports.setDeadlinesIfEligible=function(e){var n=e.getStore("CourseStore"),o=e.getStore("CourseScheduleStore"),u=e.getStore("ProgressStore"),i=s(n,o,u),a=n.hasLaunched(),d=n.getCourseId(),c=e.getStore("CourseMembershipStore").isEnrolled(),l=e.getStore("SessionStore");return!r(d)&&a&&!l.isSessionsCourse()&&1===i&&c?e.executeAction(exports.enableDeadlines,{}):t()},exports.loadCourseDeadlines=function(e,u){var i=u.userId,s=e.getStore("CourseStore").hasLaunched(),a=e.getStore("CourseStore").getCourseId(),d=e.getStore("CourseMembershipStore").isEnrolled(),c=e.getStore("SessionStore");if(!d||!i||r(a)||!s)return t();if(c.isSessionsEnabled()){if(c.isEnrolled()){var l=c.getSession(),f={moduleDeadlines:l.moduleDeadlines};f.itemDeadlines=l.itemDeadlines,e.dispatch("LOAD_COURSE_DEADLINES",f)}return t()}return n.getStartTime(a).then(o).then(function(t){var r=t.elements,n=r[0];return n?e.executeAction(exports.receiveDeadlines,{deadlines:n}):e.executeAction(exports.setDeadlinesIfEligible,{})})},exports.enableDeadlines=function(e){var t=e.getStore("CourseStore").getCourseId();return n.sendStartTime(!0,t).fail(function(e){throw e}).then(o).then(function(t){var r=t.elements,n=r[0].start;return u.pushV2(["open_course_home.welcome.emit.course_deadline_set",{first_week_due_time:n}]),e.executeAction(exports.receiveDeadlines,{deadlines:r[0]})})},exports.disableDeadlines=function(e){var t=e.getStore("CourseStore").getCourseId();return n.disableDeadlines(t).then(function(){return e.dispatch("DISABLE_DEADLINES")}).fail(function(e){throw e})},exports.resetDeadlines=function(e,t){var r=t.userId,o=e.getStore("CourseStore").getCourseId();return n.resetDeadlines(o).then(function(){return e.executeAction(exports.loadCourseDeadlines,{userId:r})}).fail(function(e){throw e})}}.apply(exports,t))&&(module.exports=r)},WXJF:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("uf8H"),e("3dUg"),e("zHM+")],void 0!==(r=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var t=e("go2y"),r=_interopRequireDefault(t),n=e("uf8H"),o=_interopRequireDefault(n),u=e("3dUg"),i=_interopRequireDefault(u),s=e("zHM+"),a=_interopRequireDefault(s);module.exports=function(e,t){if(e.getStore(i.default).hasLoaded())return(0,r.default)();if(!t)return r.default.reject(new a.default("courseId must be provided."));return(0,o.default)(t).then(function(t){e.dispatch("LOAD_COURSE_SCHEDULE",t)})}}.apply(exports,t))&&(module.exports=r)},X2hd:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("iFGm"),e("2TVH"),e("oq/K")],void 0!==(r=function(require,exports,module){"use strict";var t=e("go2y"),r=e("iFGm"),n=e("2TVH"),o=e("oq/K");module.exports=function(e){var u=new n("/api/courses.v1",{type:"rest"}),i=(new r).addQueryParam("q","slug").addQueryParam("slug",e).addQueryParam("fields","certificates").addQueryParam("showHidden",!0);return t(u.get(i.toString())).then(function(t){if("notFound"===t.errorCode)return null;var r=t.elements[0],n=r.id,u=r.certificates;return o.courseId=n,o.courseSlug=e,{courseId:n,courseCertificates:u}})}}.apply(exports,t))&&(module.exports=r)},ZuLc:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("iFGm"),e("2TVH")],void 0!==(r=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var t=e("go2y"),r=_interopRequireDefault(t),n=e("iFGm"),o=_interopRequireDefault(n),u=e("2TVH"),i=_interopRequireDefault(u),s=(0,i.default)("/api/onDemandCoursePresentGrades.v1",{type:"rest"});exports.getPresentGrade=function(e){var t=e.userId,n=e.courseId,u=new o.default("/"+t+"~"+n).addQueryParam("fields","grade,relevantItems,passingStateForecast");return(0,r.default)(s.get(u.toString()))}}.apply(exports,t))&&(module.exports=r)},aB0h:function(module,exports,e){var t=e("O9hj"),r=e("37IR");module.exports=t(r)},cjZO:function(module,exports,e){(function(_){var t,r;t=[e,exports,module,e("go2y"),e("AwHF"),e("c7Iy"),e("lGU6"),e("1/cZ")],void 0!==(r=function(require,exports,module){"use strict";var t=e("go2y"),_=e("AwHF"),r=e("c7Iy"),n=e("lGU6"),o=e("1/cZ");exports.loadUserGroupsForCourse=function(e,u){var i=u.courseId,s=u.userId;if(e.getStore("GroupSettingStore").hasLoaded())return t();return r.myCourseGroupsWithSettings(s,i).then(function(t){var r=_(t.linked["groupSettings.v1"]).map(function(e){return new o(e)}),u=t.linked["groups.v1"].map(function(e){return new n(e)}),i=t.elements;e.dispatch("LOADED_COURSE_GROUPS",{groups:u,groupSettings:r,groupMemberships:i})}).fail(function(t){e.dispatch("LOADED_COURSE_GROUPS",{})})},exports.loadUserSessionGroupForCourse=function(e,n){var o=n.courseId,u=n.userId,i=n.sessionId;if(e.getStore("GroupSettingStore").hasSessionGroupLoaded())return t();return r.getCourseSessionGroup(u,o,i).then(function(t){var r=t.elements[0];e.dispatch("LOADED_SESSION_GROUP",{sessionGroup:r})}).fail(function(t){e.dispatch("LOADED_SESSION_GROUP",{})})}}.apply(exports,t))&&(module.exports=r)}).call(exports,e("AwHF"))},eoPD:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("2TVH"),e("lDcb")],void 0!==(r=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var t=e("go2y"),r=_interopRequireDefault(t),n=e("2TVH"),o=_interopRequireDefault(n),u=e("lDcb"),i=_interopRequireDefault(u);module.exports=function(e){var t=(0,o.default)("/api/domains.v1",{type:"rest"});if(void 0!==e.getStore(i.default).domains)return(0,r.default)();return(0,r.default)(t.get("?fields=id,name")).then(function(t){e.dispatch("LOAD_DOMAINS",t.elements)})}}.apply(exports,t))&&(module.exports=r)},eoZB:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("iFGm"),e("LwZ5")],void 0!==(r=function(require,exports,module){"use strict";function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var t=e("go2y"),r=_interopRequireDefault(t),n=e("iFGm"),o=_interopRequireDefault(n),u=e("LwZ5"),i=_interopRequireDefault(u);module.exports=function(e,t){var n=(0,i.default)("/api/onDemandReferences.v1",{type:"rest"}),u=(new o.default).addQueryParam("courseId",t).addQueryParam("q","courseListed").addQueryParam("fields","name,shortId,slug,content").addQueryParam("includes","assets");return(0,r.default)(n.get(u.toString())).then(function(t){e.dispatch("LOAD_REFERENCES_LIST",t.elements)})}}.apply(exports,t))&&(module.exports=r)},"k+WV":function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("LwZ5"),e("iFGm"),e("8k/k")],void 0!==(r=function(require,exports,module){"use strict";var t=e("go2y"),r=e("LwZ5"),n=e("iFGm"),o=e("8k/k"),u=r("/api/onDemandDeadlineSettings.v1",{type:"rest"}),i={getStartTime:function getStartTime(e){var r=(new n).addQueryParam("q","byUserAndCourse").addQueryParam("userId",o.get().id).addQueryParam("courseId",e).toString();return t(u.get(r)).fail(function(e){console.error(e)})},sendStartTime:function sendStartTime(e,r){var n={data:{userId:o.get().id,courseId:r,start:Date.now(),isEnabled:e}};return t(u.post("",n))},disableDeadlines:function disableDeadlines(e){return i.sendStartTime(!1,e)},getResetPreview:function getResetPreview(e,r){var i=(new n).addQueryParam("q","extendPreview").addQueryParam("userId",o.get().id).addQueryParam("courseId",e).addQueryParam("extendedAt",Date.now()).toString();t(u.get(i)).then(r).fail(function(e){console.error(e)}).done()},resetDeadlines:function resetDeadlines(e){var r={data:{userId:o.get().id,courseId:e,extendedAt:Date.now()}},i=(new n).addQueryParam("action","extend").toString();return t(u.post(i,r))}};module.exports=i}.apply(exports,t))&&(module.exports=r)},qDvH:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("ZuLc")],void 0!==(r=function(require,exports,module){"use strict";var t=e("go2y"),r=e("ZuLc");exports.loadCoursePresentGrade=function(e,n){var o=n.userId,u=n.courseId;if(e.getStore("CoursePresentGradeStore").hasLoaded())return t();return o?t(r.getPresentGrade({userId:o,courseId:u})).then(function(t){var r=t.elements[0];e.dispatch("LOAD_COURSE_PRESENT_GRADE",{presentGrade:r})}).fail(function(t){e.dispatch("LOAD_COURSE_PRESENT_GRADE_FAIL",{})}):(e.dispatch("LOAD_COURSE_PRESENT_GRADE_FAIL",{}),t())}}.apply(exports,t))&&(module.exports=r)},rB0C:function(module,exports,e){(function(_){var t,r;t=[e,exports,module,e("AwHF"),e("aB0h"),e("2294"),e("0jrO"),e("M+4A")],void 0!==(r=function(require,exports,module){"use strict";var _=e("AwHF"),t=e("aB0h"),r=e("2294"),n=e("0jrO"),o=e("M+4A"),u=o.Sessions;module.exports=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return t(e).then(function(t){if(t.linked&&t.linked["onDemandSessions.v1"]&&t.linked["onDemandSessionMemberships.v1"]){var o=_(t.linked["onDemandSessions.v1"]).groupBy("courseId"),i=_(t.linked["onDemandSessionMemberships.v1"]).groupBy("sessionId"),s=Object.keys(i);t.elements.forEach(function(e){var t=o[e.courseId]||[];if(t.length){var r=t.filter(function(e){return s.indexOf(e.id)>=0});if(r.length){var n=new u(r).getLastSession();e.onDemandSessionId=n.id,e.onDemandSessionMemberships=r.map(function(e){return i[e.id]})}}})}if(t.linked&&t.linked["v1Details.v1"]&&(t.linked["courses.v1"]=_(t.linked["courses.v1"]).map(function(e){if("v1.session"===e.courseType||"v1.capstone"===e.courseType){e.v1Details=e.id;var r=_(t.linked["v1Sessions.v1"]).reduce(function(t,r){return r.courseId===e.id&&t.push(r.id.toString()),t},[]);e.v1Sessions=r}return e})),t.linked&&t.linked["v2Details.v1"]&&(t.linked["courses.v1"]=_(t.linked["courses.v1"]).map(function(e){return"v2.ondemand"===e.courseType&&(e.v2Details=_(t.linked["v2Details.v1"]).findWhere({id:e.id})),e})),t.linked&&t.linked["vcMemberships.v1"]){var a=_(t.linked["vcMemberships.v1"]).pluck("id");t.elements=_(t.elements).map(function(e){return _(a).contains(e.id)&&(e.vcMembershipId=e.id),e})}if(t.linked&&t.linked["courses.v1"]){var d=_(t.linked["courses.v1"]).pluck("id");t.elements=_(t.elements).chain().filter(function(e){return _(d).contains(e.courseId)}).compact().value()}if(t.linked&&t.linked["signatureTrackProfiles.v1"]&&_(t.elements).each(function(e){e.signatureTrackProfile=e.userId}),e.rawData)return t;if(e.withPaging)return{elements:r(n.prototype.resourceName,t),paging:t.paging};return r(n.prototype.resourceName,t)}).fail(function(t){if(e.rawData)return null;return new n})}}.apply(exports,t))&&(module.exports=r)}).call(exports,e("AwHF"))},tltj:function(module,exports,e){var t,r;t=[e,exports,module,e("go2y"),e("iFGm"),e("LwZ5")],void 0!==(r=function(require,exports,module){"use strict";var t=e("go2y"),r=e("iFGm"),n=e("LwZ5"),o=new n("/api/onDemandCourseSchedules.v1");exports.getCourseSchedule=function(e){var n=new r(e).addQueryParam("fields","defaultSchedule");return t(o.get(n.toString()))}}.apply(exports,t))&&(module.exports=r)},uf8H:function(module,exports,e){var t,r;t=[e,exports,module,e("tltj"),e("WXze")],void 0!==(r=function(require,exports,module){"use strict";var t=e("tltj"),r=t.getCourseSchedule,n=e("WXze");module.exports=function(e){if(!e)throw new Error("`courseId` is required to get course schedule.");return r(e).then(n).then(function(e){return e.elements[0].defaultSchedule.periods})}}.apply(exports,t))&&(module.exports=r)}});
//# sourceMappingURL=27.521ce18b3766b1234c92.js.map