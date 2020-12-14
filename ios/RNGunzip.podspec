
Pod::Spec.new do |s|
  s.name         = "RNGunzip"
  s.version      = "1.0.0"
  s.summary      = "RNGunzip"
  s.description  = <<-DESC
                  RNGunzip
                   DESC
  s.homepage     = "https://github.com/bartolomeon/react-native-gunzip"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNGunzip.git", :tag => "master" }
  s.source_files  = "RNGunzip/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
