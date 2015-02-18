function PNGHandler() {
  var self = this;

  this.na = navigator.appName.toLowerCase();
  this.nv = navigator.appVersion.toLowerCase();
  this.isIE = this.na.indexOf('internet explorer')+1?1:0;
  this.isWin = this.nv.indexOf('windows')+1?1:0;
  this.ver = this.isIE?parseFloat(this.nv.split('msie ')[1]):parseFloat(this.nv);
  this.isMac = this.nv.indexOf('mac')+1?1:0;
  this.isOpera = (navigator.userAgent.toLowerCase().indexOf('opera ')+1 || navigator.userAgent.toLowerCase().indexOf('opera/')+1);
  if (this.isOpera) this.isIE = false;

  this.transform = null;

  this.getElementsByClassName = function(className,oParent) {
    doc = (oParent||document);
    matches = [];
    nodes = doc.all||doc.getElementsByTagName('*');
    for (i=0; i<nodes.length; i++) {
      if (nodes[i].className == className || nodes[i].className.indexOf(className)+1 || nodes[i].className.indexOf(className+' ')+1 || nodes[i].className.indexOf(' '+className)+1) {
        matches[matches.length] = nodes[i];
      }
    }
    return matches;
  }

  this.filterMethod = function(oOld) {
    var o = document.createElement('div');
    var filterID = 'DXImageTransform.Microsoft.AlphaImageLoader';
    if (oOld.nodeName == 'DIV') {
      var b = oOld.currentStyle.backgroundImage.toString();
      oOld.style.backgroundImage = 'none';
      var i1 = b.indexOf('url("')+5;
      var newSrc = b.substr(i1,b.length-i1-2).replace('.gif','.png');
      o = oOld;
      o.style.writingMode = 'lr-tb';
      o.style.filter = "progid:"+filterID+"(src='"+newSrc+"',sizingMethod='crop')";
    } else if (oOld.nodeName == 'IMG') {
      var newSrc = oOld.getAttribute('src').replace('.gif','.png');
      oOld.src = 'images/none.gif';
      oOld.style.filter = "progid:"+filterID+"(src='"+newSrc+"',sizingMethod='crop')";
      oOld.style.writingMode = 'lr-tb';
    }
  }

  this.pngMethod = function(o) {
    bgImage = this.getBackgroundImage(o);
    if (bgImage) {
      o.style.backgroundImage = 'url('+bgImage.replace('.gif','.png')+')';
    } else if (o.nodeName == 'IMG') {
      o.src = o.src.replace('.gif','.png');
    } else if (!this.isMac) {
    }
  }

  this.getBackgroundImage = function(o) {
    var b, i1;
    var bgUrl = null;

    if (o.nodeName == 'DIV' && !(this.isIE && this.isMac)) {
      if (document.defaultView) {
        if (document.defaultView.getComputedStyle) {
          b = document.defaultView.getComputedStyle(o,'').getPropertyValue('background-image');
          i1 = b.indexOf('url(')+4;
          bgUrl = b.substr(i1,b.length-i1-1);
        } else {
        }
      } else {
      }
    }
    return bgUrl;
  }
  
  this.supportTest = function() {
    if (this.isIE && this.isWin && this.ver >= 5.5) {
      self.transform = self.filterMethod;
    } else if (!this.isIE && this.ver < 5) {
      self.transform = null;
    } else if (!this.isIE && this.ver >= 5 || (this.isIE && this.isMac && this.ver >= 5)) {
      self.transform = self.pngMethod;
    } else {
      self.transform = null;
      return false;
    }
    return true;
  }

  this.init = function() {
    if (this.supportTest()) {
      this.elements = this.getElementsByClassName('png');
      for (var i=0; i<this.elements.length; i++) {
        this.transform(this.elements[i]);
      }
    }
  }

}

var pngHandler = new PNGHandler();