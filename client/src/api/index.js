/**
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/20 20:52
 * @description：请求拦截器
 * @update: 2021/8/20 20:52
 */
import Axios from 'axios'

const axiosInstance = Axios.create({
  baseURL: '/api',
  timeout: 5000
})
axiosInstance.interceptors.request.use(
  (config) => {
    // 请求发送成功后拦截 config就是axios的config
    // 拦截后进行相应的逻辑处理

    return config // 放行
  },
  (error) => {
    // 请求发送失败后拦截

    // 拦截后进行相应的逻辑处理

    return error // 放行
  }
)

axiosInstance.interceptors.response.use(
  (result) => {
    // 对请求成功的响应进行拦截 result就是响应结果
    // 拦截后进行相应的逻辑处理

    return result // 放行
  },
  (error) => {
    // 对请求失败的响应进行拦截

    // 拦截后进行相应的逻辑处理

    return error // 放行
  }
)

export const requestParams = (url, params = null, method = 'post') => {
  return axiosInstance({
    url,
    method,
    params
  })
}

export const requestData = (url, data = null, method = 'post') => {
  return axiosInstance({
    url,
    method,
    data
  })
}
