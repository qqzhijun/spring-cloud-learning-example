package com.lidong.es.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章详情
 *
 * String indexName();//索引库的名称，个人建议以项目的名称命名
 * String type() default "";//类型，个人建议以实体的名称命名
 * short shards() default 5;//默认分区数
 * short replicas() default 1;//每个分区默认的备份数
 * String refreshInterval() default "1s";//刷新间隔
 * String indexStoreType() default "fs";//索引文件存储类型
 *
 **/

@Document(indexName = "index_jkgg", type = "es_artilcle_detail_info")
public class EsArticleDetailInfoEntity implements  Serializable {


	private static final long serialVersionUID = 5062595780475144100L;
	/**
	 * 
	 */
	@Id
	private Integer articleId;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * description 文章导读
	 */
	private String description;
	/**
	 * 类型 1 是资讯 2是科室
	 */
	private Integer type;
	/**
	 * 科室 - 二级 分类id
	 */
	private Integer classId;
	/**
	 * 文章的url
	 */
	private String articleUrl;
	/**
	 * 富文本内容
	 */
	@Field(searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
	private String content;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 标题
	 */
	@Field(searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
	private String title;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 主图片
	 */
	private String image;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 浏览量
	 */
	private Integer viewNumber;
	/**
	 * 分享量
	 */
	private Integer shareView;
	/**
	 * 文章属性 1 首页推荐  2 原创
	 */
	private String attribute;
	/**
	 * 审核状态 1 待审核 2 审核通过 3 审核不通过
	 */
	private Integer auditStatus;
	/**
	 * 静态化后文件存储路径
	 */
	private String staticFileUrl;
	/**
	 * 删除  0 删除 1 未删除
	 */
	private Integer isDelete;
	/**
	 * 是否指定 0 正常  1 置顶
	 */
	private Integer isTop;

	/**
	 * 标签
	 */
	private String tags;


	/**
	 * 首页推荐
	 */
	private  Integer isHpr;
	/**
	 *
	 */
    private Integer isOriginal;
	/**
	 * 二级分类
	 */
	private Integer secClassId;

	/**
	 * 是否是视频
	 */
	private Integer isVedio;

	/**
	 * 视频的url
	 */
	private String vedioUrl;
	/**
	 * 视频封面的url
	 */
	private String vedioCoverUrl;

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public String getVedioCoverUrl() {
		return vedioCoverUrl;
	}

	public void setVedioCoverUrl(String vedioCoverUrl) {
		this.vedioCoverUrl = vedioCoverUrl;
	}

	public Integer getIsVedio() {
		return isVedio;
	}

	public void setIsVedio(Integer isVedio) {
		this.isVedio = isVedio;
	}

	public Integer getIsHpr() {
		return isHpr;
	}

	public void setIsHpr(Integer isHpr) {
		this.isHpr = isHpr;
	}

	public Integer getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(Integer isOriginal) {
		this.isOriginal = isOriginal;
	}

	public Integer getSecClassId() {
		return secClassId;
	}

	public void setSecClassId(Integer secClassId) {
		this.secClassId = secClassId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * 设置：
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	/**
	 * 获取：
	 */
	public Integer getArticleId() {
		return articleId;
	}
	/**
	 * 设置：关键字
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 获取：关键字
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * 设置：description 文章导读
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：description 文章导读
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：类型 1 是资讯 2是科室
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型 1 是资讯 2是科室
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：科室 - 二级 分类id
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	/**
	 * 获取：科室 - 二级 分类id
	 */
	public Integer getClassId() {
		return classId;
	}
	/**
	 * 设置：文章的url
	 */
	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}
	/**
	 * 获取：文章的url
	 */
	public String getArticleUrl() {
		return articleUrl;
	}
	/**
	 * 设置：富文本内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：富文本内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：主图片
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：主图片
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：浏览量
	 */
	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}
	/**
	 * 获取：浏览量
	 */
	public Integer getViewNumber() {
		return viewNumber;
	}
	/**
	 * 设置：分享量
	 */
	public void setShareView(Integer shareView) {
		this.shareView = shareView;
	}
	/**
	 * 获取：分享量
	 */
	public Integer getShareView() {
		return shareView;
	}
	/**
	 * 设置：文章属性 1 首页推荐  2 原创
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	/**
	 * 获取：文章属性 1 首页推荐  2 原创
	 */
	public String getAttribute() {
		return attribute;
	}
	/**
	 * 设置：审核状态 1 待审核 2 审核通过 3 审核不通过
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态 1 待审核 2 审核通过 3 审核不通过
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：静态化后文件存储路径
	 */
	public void setStaticFileUrl(String staticFileUrl) {
		this.staticFileUrl = staticFileUrl;
	}
	/**
	 * 获取：静态化后文件存储路径
	 */
	public String getStaticFileUrl() {
		return staticFileUrl;
	}
	/**
	 * 设置：删除  0 删除 1 未删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：删除  0 删除 1 未删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：是否指定 0 正常  1 置顶
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
	 * 获取：是否指定 0 正常  1 置顶
	 */
	public Integer getIsTop() {
		return isTop;
	}



}

