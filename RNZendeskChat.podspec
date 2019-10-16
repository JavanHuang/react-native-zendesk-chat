require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "RNZendeskChat"
  s.version      = package['version']
  s.summary      = package['description']
  s.license      = package['license']

  s.authors      = package['author']
  s.homepage     = package['homepage']

  s.source       = { :git => "https://github.com/zanechua/react-native-zendesk-chat.git" }
  s.source_files  = "ios/**/*.{h,m}"
  s.platform          = :ios, '9.0'

  s.dependency 'React'
  s.dependency 'ZDCChat'
  s.static_framework = true
end
