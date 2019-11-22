# analog-access
模拟访问

### 配置文件
```yaml
# CSDN 配置
csdn:
  # 博客主页
  blogPage:
    # 博客主页地址
    url: https://tudan.blog.csdn.net
    # 分页链接模板
    listUrlTemplate: ${csdn.blogPage.url}/article/list/{}?
    # 匹配 pageSize，前置特殊字符串
    pageSizeStart: pageSize
    # 匹配 listTotal，前置特殊字符串
    listTotalStart: listTotal
    # 匹配 listTotal，后置特殊字符串
    listTotalEnd: pageQueryStr
  # 文章
  article:
    # 粗略匹配字符
    original:
      start: class="article-item-box csdn-tracking-statistics"
      end: <p class="content">
    # 精确匹配字符，因为有特殊字符，所有用单引号引起来 ''
    identification:
      start: '<a href="'
      end: '" target="_blank">'

# 定时器配置
schedule:
  initialDelayString: 0
  fixedDelayString: 600000

# 系统配置
server:
  port: 8888

```