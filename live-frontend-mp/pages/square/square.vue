<template>
  <view class="square-root">
    <u-row customStyle="height: 60rpx">
       <!--  <u-col span="7" offset="1">
            <u-search placeholder="搜索" v-model="keyword" :showAction="false"></u-search>
        </u-col>
        <u-col span="4">
            <view class="demo-layout bg-purple"></view>
        </u-col> -->
        <fy-dropdown :menuList="menuList" title-size="30rpx">
          <fy-dropdown-item v-model="synthesisValue" dropdownKey="synthesis" :options="columnOptions" type="column" 
          @change="handlerColumnChange"></fy-dropdown-item>
          <fy-dropdown-item v-model="priceValue" dropdownKey="price" :options="priceOptions" @change="handlerTypeChange">

          </fy-dropdown-item>
          <fy-dropdown-item v-model="salesValue" dropdownKey="sales" :options="salesOptions" @change="handlerTypeChange"></fy-dropdown-item>
        </fy-dropdown>
    </u-row>
   <!--  <u-row customStyle="heihg">
      <u-col span="10">
        <view :class="testan"></view>
      </u-col>
    </u-row> -->

    <u-list @scrolltolower="loadMomentInfo" customStyle ="height: calc(100% - 60rpx)">
      <u-list-item v-for="(moment, index) in momentInfo.momentList" :key="index">
        <moment :moment="moment" momentPageType="LIST" v-on:updateMomentLikeCount="updateMomentLikeCount($event, moment)"
          v-on:updateMomentHasLiked="updateMomentHasLiked($event, moment)">
        </moment>
      </u-list-item>
      <u-loadmore 
				loadmoreText="看,我和别人不一样"
				color="#1CD29B"
				lineColor="#1CD29B"
				dashed
				line
    />
    </u-list>
    <image class="open-popup" @click="openPopup"
        src="../../static/icon/fabu.png"
        mode="aspectFill"
      >
    </image>

    <uni-popup ref="popup" type="bottom">
      <view class="popup">
        <view class="popup-publish">
          <view class="popup-publish-image-wrapper" @click="toPublishMoment">
            <image class="popup-publish-image-moment" src="../../static/icon/publish-moment.png" mode="aspectFill">
            </image>
          </view>
          <view>发动态</view>
        </view>
        <view class="popup-publish">
          <view class="popup-publish-image-wrapper" @click="toPublishVideo">
            <image class="popup-publish-image-video" src="../../static/icon/publish-video.png" mode="aspectFill">
            </image>
          </view>
          <view>发布视频</view>
        </view>
      </view>
    </uni-popup>
  </view>
</template>

<script>
import uniPopup from '@/components/uni-popup/uni-popup.vue';
import httpUtils from '../../common/util/httpUtils.js';
import moment from '../../components/moment/moment.vue';
import videoItem from '../../components/videoItem/videoItem.vue';
import imageItem from '../../components/imageItem/imageItem.vue';
import { callbackify } from 'util';
export default {
  components: {
    moment,
    videoItem,
    imageItem,
    uniPopup
},

  data() {
    return {
    
      // 动态数据
      momentInfo: {
        pageNo: 1,
        pageSize: 20,
        momentList: []
      },
      status: 'loadmore',
      keyword: '遥看瀑布挂前川',
      testvh: 30,
      testvh2: "100%",
      menuList: [{ title: '综合排序', dropdownKey: 'synthesis' }, { title: '价格', dropdownKey: 'price' }, { title: '销量', dropdownKey: 'sales' }],
      synthesisValue: '',
      columnOptions: [
                { title: '任务类型', key: 'type', default: '', list: [{ text: '全部', value: '' }, { text: '类型1', value: '2' }, { text: '类型2', value: '1' }] },
                { title: '任务状态', key: 'status', default: '', list: [{ text: '全部', value: '' }, { text: '已完结', value: '1' }, { text: '工作中', value: '2' }] },
            ],
      priceValue: '',
      priceOptions: [{ label: '筛选条件1', value: 1 }, { label: '筛选条件2', value: 2 }, { label: '筛选条件3', value: 3 }],
      salesValue: '',
      salesOptions: [{ label: '筛选条件3', value: 1 }, { label: '筛选条件33', value: 2 }, { label: '筛选条件333', value: 3 }],
    }
  },

  async onLoad() {
    // 加载moment数据
    await this.loadMomentInfo();
    /*   // 加载video数据
      await this.loadVideoInfo();
      // 加载image数据
      await this.loadImageInfo(); */

    uni.$on('updateMomentHasFollowed', event => {
      console.log("trig", event)
      for (let i in this.momentInfo.momentList) {
        let moment = this.momentInfo.momentList[i];
        if (moment.id === event.id) {
          moment.hasFollowed = event.hasFollowed;
          break;
        }
      }
      for (let i in this.videoInfo.momentList) {
        let moment = this.videoInfo.momentList[i];
        if (moment.id === event.id) {
          moment.hasFollowed = event.hasFollowed;
          break;
        }
      }
    });
  },

 
  methods: {
    // 界面相关函数
    // 点击tab导航
    clikeTabNav(e) {
      let newIndex = e.target.dataset.index;
      this.updateCurTabIndex(newIndex);
    },
    // 事件相关函数
    // 打开弹层
    openPopup() {
      this.$refs.popup.open();
    },

    // 去发动态
    toPublishMoment() {
      uni.navigateTo({
        url: `../publishMoment/publishMoment?publishType=MOMENT`
      });
    },

    // 去发视频
    toPublishVideo() {
      uni.chooseVideo({
        count: 1,
        compressed: false,
        sourceType: ['album'],
        success: (res) => {
          console.log(res);
          if (res.size > 1024 * 1024 * 1024) {
            uni.showToast({
              title: "视频最大为1G",
              duration: 2000
            });
            return;
          }

          uni.navigateTo({
            url: `../publishMoment/publishMoment?publishType=VIDEO&tempVideoPath=${res.tempFilePath}&videoOriginalTitle=${res.name}&videoWidth=${res.width}&videoHeight=${res.height}`
          });
        }
      })
    },

    updateMomentLikeCount(event, moment) {
      moment.likeCount = event;
    },

    updateMomentHasLiked(event, moment) {
      moment.hasLiked = event;
    },

    // 数据相关函数
    // 加载moment数据
    async loadMomentInfo() {
      httpUtils.postJson("/square/querySquareMoment", {
        pageNo: this.momentInfo.pageNo,
        pageSize: this.momentInfo.pageSize
      })
      let [data] = await httpUtils.postJson("/square/querySquareMoment", {
        pageNo: this.momentInfo.pageNo,
        pageSize: this.momentInfo.pageSize
      });
      if(data.body.length == 0){
        this.status = 'nomore'
        return
      }
      this.momentInfo.momentList = this.momentInfo.momentList.concat(data.body);
      this.momentInfo.pageNo++;
    },

    // 加载video数据
    async loadVideoInfo() {
      let [data] = await httpUtils.postJson("/square/querySquareMoment", {
        pageNo: this.videoInfo.pageNo,
        pageSize: this.videoInfo.pageSize,
        momentType: 'VIDEO',
        repostMomentId: 0
      });
      this.videoInfo.momentList = this.videoInfo.momentList.concat(data.body);
      this.videoInfo.pageNo++;
    },

    // 加载image数据
    async loadImageInfo() {
      let [data] = await httpUtils.postJson("/square/querySquareImage", {
        pageNo: this.imageInfo.pageNo,
        pageSize: this.imageInfo.pageSize
      });
      let dateToImageList = data.body;
      for (let date in dateToImageList) {
        if (!this.imageInfo.imageMap[date]) {
          this.imageInfo.imageMap[date] = [];
        }
        this.imageInfo.imageMap[date] = this.imageInfo.imageMap[date].concat(dateToImageList[date]);
      }
      this.imageInfo.pageNo++;
      console.log(this.imageInfo)
    },
    handlerColumnChange(e) {
            if (e.cancel) return;

            this.columnOptions.forEach(item=>{
                item.default = e.data[item.key];
            })
        },
    handlerTypeChange(e) {
            console.log(e);
    },
  }

}
</script>

<style>
.square-root {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
}

.line {
  height: 2 rpx;
  background-color: #f3f3f3;
}

/* tab导航 */
.tab-nav-main-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: center;
  border-bottom: 2 rpx solid #f3f3f3;
}

.tab-nav-main {
  display: inline-flex;
  flex-direction: row;
  justify-content: center;
  position: relative;
}

.tab-nav-list {
  display: flex;
  flex-direction: row;
  justify-content: center;
  font-size: 0;
}

.tab-nav-item {
  font-size: 30 rpx;
  /* line-height: 76rpx; */
  padding: 0 20 rpx;
  padding-bottom: 20 rpx;
  margin: 0 8 rpx;
}

.tab-nav-item-active {
  font-weight: bold;
}

.tab-nav-underline {
  position: absolute;
  bottom: -2 rpx;
  left: 4 rpx;
  height: 4 rpx;
  border-radius: 2 rpx;
  background-color: #f6423d;
}

.tab-nav-underline-animation {
  transition-duration: 200ms;
  transition-property: left;
}

/* tab内容 */
.tab-content {
  height: 100%;
}

.tab-content-item {
  overflow-y: auto;
}

/* 发布动态按钮 */
.open-popup {
  position:absolute;
  bottom: 90rpx;
  right: 30rpx;
  width: 88rpx;
  height: 88rpx;
}




/* 发动态弹层 */
.popup {
  /* #ifdef H5 */
  height: 320 rpx;
  /* #endif */
  /* #ifndef H5 */
  height: 220 rpx;
  /* #endif */

  border-top-left-radius: 50 rpx;
  border-top-right-radius: 50 rpx;
  background-color: #ffffff;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: flex-start;
  padding-top: 40 rpx;
  font-size: 30 rpx;
  line-height: 2;
  color: #7F7F7F;
  text-align: center;
}

.popup .popup-publish-image-wrapper {
  background-color: #fd4445;
  width: 132 rpx;
  height: 132 rpx;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.popup-publish-image-moment {
  width: 60 rpx;
  height: 60 rpx;
}

.popup-publish-image-video {
  width: 64 rpx;
  height: 64 rpx;
}

.testan{
  display: flex;
  flex: 1;
}
</style>
